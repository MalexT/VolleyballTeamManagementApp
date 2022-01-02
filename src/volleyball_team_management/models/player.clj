(ns volleyball-team-management.models.player
  (:refer-clojure :exclude [seqable? get update])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]))

(def db (edn/read-string (slurp "config/db-config.edn")))

(defn allPlayers []
  (jdbc/query db
              ["SELECT p.playerId,
	                   p.name,
	                   LEFT(CAST(p.dateOfBirth as char),10) as dateofBirth,
	                   p.height,
	                   t.name as teamName,
	                   pos.name as posName
                FROM players p
                JOIN teams t ON p.teamId = t.teamId
                JOIN position pos ON p.positionId = pos.positionId"]))


(defn deletePlayer [id]
  (jdbc/delete! db :players ["playerId = ?" id]))

(defn get [id]
  (first (jdbc/query db ["SELECT * 
                          FROM players
                          WHERE playerId = ?" id])))

(defn update [id parameters]
  (jdbc/update! db :players parameters ["playerId = ?" id]))

(defn insertPlayer [parameters]
  (jdbc/insert! db :players parameters))