(ns volleyball-team-management.models.player
  (:refer-clojure :exclude [get update])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql]))

(def db (clojure.edn/read-string (slurp "config/db-config.edn")))

(defn allPlayers []
  (jdbc/query db
              ["SELECT p.playerId,
	                   p.name,
	                   p.dateOfBirth,
	                   p.height,
	                   t.name,
	                   pos.name
                FROM players p
                JOIN teams t ON p.teamId = t.teamId
                JOIN position pos ON p.positionId = pos.positionId"]))

(defn deletePlayer [id]
  (jdbc/delete! db :players (sql/where {:playerId id})))

(defn get [id]
  (first (jdbc/query db
                     (sql/select * :players (sql/where {:playerId id})))))

(defn update [id parameters]
  (jdbc/update! db :players parameters (sql/where {:playerId id})))

(defn insertPlayer [parameters]
  (jdbc/insert! db :players parameters))