(ns volleyball-team-management.models.team
  (:refer-clojure :exclude [seqable? get update])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]))

(def db (edn/read-string (slurp "config/db-config.edn")))

(defn allTeams []
  (jdbc/query db
              ["SELECT t.teamId,
                t.name,
                l.name AS league,
                t.city,
                t.state,
                t.foundationDate
                FROM teams t
                JOIN league l ON t.leagueId = l.leagueId"]))

(defn deleteTeam [id]
  (jdbc/delete! db :teams ["teamId = ?" id]))

(defn get [id]
  (first (jdbc/query db ["SELECT * 
                          FROM teams
                          WHERE teamId = ?" id])))

(defn update [id parameters]
  (jdbc/update! db :teams parameters ["teamId = ?" id]))

(defn insertTeam [params]
  (jdbc/insert! db :teams params))