(ns aoc-20.day-13
  (:require [aoc-20.util :as util]
            [clojure.string :as s]))

(defn read-lines [filename]
  (util/read-lines filename))

;; -- Part 1 --

(defn bus-ids [input]
  (map #(Integer/parseInt %) (-> (s/replace input #"x," "")
                                 (s/split #","))))
(defn check-bus [goal m id]
  (into m (loop [bus-time id]
            (if (>= bus-time goal)
              {bus-time id}
              (recur (+ bus-time id))))))

(defn part-1 [input]
  (let [goal  (Integer/parseInt (first input))
        buses (bus-ids (second input))
        times (reduce (partial check-bus goal) {} buses)
        earliest (first (sort (keys times)))
        bus-id   (get times earliest)]
    (-> (- earliest goal)
        (* bus-id))))

(part-1 (read-lines "day13-example-input.txt"))
(part-1 (read-lines "day13-input.txt"))
