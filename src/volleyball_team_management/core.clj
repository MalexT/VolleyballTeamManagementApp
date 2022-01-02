(ns volleyball-team-management.core
  (:use compojure.core)
  (:require [clojure.java.jdbc :as j]
            [clojure.edn :as edn]
            [compojure.route :as route]
            [ring.middleware.basic-authentication :refer :all]
            [ring.util.response :as resp]
            [compojure.handler :as hanlder]
            [volleyball-team-management.models.player :as player]
            [volleyball-team-management.models.team :as team]
            [volleyball-team-management.models.league :as league]
            [volleyball-team-management.models.position :as position]
            [volleyball-team-management.controller.controller :as controller]))

(def db (edn/read-string (slurp "config/migration-db-config.edn")))

(defn exec-sql-file []
  (j/db-do-commands
   {:connection-uri (format "jdbc:%s://%s/%s?user=%s&password=%s"
                            (db :adapter) (db :server-name)
                            (db :database-name) (db :user-name)
                            (db :password))}
   (read-string (slurp (format "src/volleyball_team_management/migration/%s"
                               (db :migration-file-name))))))


(defn init []
  (j/db-do-commands {:connection-uri (format "jdbc:%s://%s?user=%s&password=%s"
                                             (db :adapter) (db :server-name)
                                             (db :user-name) (db :password))}
                    false
                    (format "CREATE DATABASE IF NOT EXISTS %s", (db :database-name)))
  (exec-sql-file))

(defn destroy []
    (j/db-do-commands {:connection-uri (format "jdbc:%s://%s?user=%s&password=%s"
                                               (db :adapter) (db :server-name)
                                               (db :user-name) (db :password))}
                      false
                      (format "DROP DATABASE IF EXISTS %s", (db :database-name))))

(defroutes public-routes
  (GET "/" [] (controller/index))
  (route/resources "/")

  (GET "/index" [] (controller/index))
  (route/resources "/")

  (GET "/teams" [] (controller/teams))
  (route/resources "/")

  (GET "/players" [] (controller/players))
  (route/resources "/")

  (GET "/models/players/create" [] (controller/createPlayer))
  (route/resources "/")

  (GET "/models/teams/create" [] (controller/createTeam))
  (route/resources "/")

  (POST "/models/teams/insert" [& params]
    (do (team/insertTeam params)
        (resp/redirect "/teams")))

  (POST "/models/players/insert" [& params]
    (do (player/insertPlayer params)
        (resp/redirect "/players")))

  (GET "/models/players/:id/delete" [id]
    (do (player/deletePlayer id)
        (resp/redirect "/players")))

  (GET "/models/teams/:id/delete" [id]
    (do (team/deleteTeam id)
        (resp/redirect "/teams")))

  (GET "/models/players/:id/update" [id] (controller/updatePlayer id))

  (POST "/models/players/:playerId/update" [& params]
    (do (player/update (:playerId params) params)
        (resp/redirect "/players")))

  (GET "/models/teams/:id/update" [id] (controller/updateTeam id))

  (POST "/models/teams/:teamId/update" [& params]
    (do (team/update (:teamId params) params)
        (resp/redirect "/teams"))))

(defroutes app-routes
  public-routes
  (route/not-found "404 Not Found"))

(def -app
  (hanlder/site app-routes))