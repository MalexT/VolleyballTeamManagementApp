(ns volleyball-team-management.core
  (:use compojure.core)
  (:require [clojure.java.jdbc :as j]
            [clojure.edn :as edn]
            [compojure.handler :as hanlder]
            [compojure.route :as route]
            [volleyball-team-management.models.player :as player]
            [volleyball-team-management.models.team :as team]
            [volleyball-team-management.models.player :as league]
            [volleyball-team-management.models.player :as position]))

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
                    (format "CREATE DATABASE %s", (db :database-name)))
  (exec-sql-file))

(defroutes public-routes
  (GET "/" [] )
  (route/resources "/"))

(defroutes app-routes
  public-routes
  (route/not-found "404 Not Found"))

(defn app []
  hanlder/site app-routes)
