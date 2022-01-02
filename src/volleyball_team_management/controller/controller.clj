(ns volleyball-team-management.controller.controller
  (:require
   [volleyball-team-management.models.player :as player]
   [volleyball-team-management.models.team :as team]
   [volleyball-team-management.models.league :as league]
   [volleyball-team-management.models.position :as position]
   [clostache.parser :as clostache]
   [clojure.java.io :as io]))

(defn read-template [template-name]
  (slurp (io/resource
          (str "views/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))

(defn index []
  (render-template "index" {}))

(defn teams []
  (render-template "teams" {:teams (team/allTeams)}))

(defn createTeam []
  (render-template "createTeam" {:leagues (league/allLeagues)}))

(defn updateTeam [id]
  (render-template "updateTeam" {:teams (team/get id)
                                 :leagues (league/allLeagues)}))

(defn players []
  (render-template "players" {:players (player/allPlayers)}))

(defn createPlayer []
  (render-template "createPlayer" {:teams (team/allTeams)
                                   :positions (position/allPositions)}))

(defn updatePlayer [id]
  (render-template "updatePlayer" {:player (player/get id)
                                   :teams (team/allTeams)
                                   :positions (position/allPositions)}))
(defn searchTeams [])