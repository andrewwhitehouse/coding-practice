(ns queen-attack
  (:require [clojure.string :as str]))

(defn- pieces-by-row-col [pieces]
  (reduce
    (fn [acc [color [row col]]]
      (assoc-in acc [row col] (str/upper-case (name color))))
    {}
    pieces))

(defn board-string [pieces]
  (let [piece-locations (pieces-by-row-col pieces)
        with-newline (fn [s] (str s "\n"))
        board (for [row (range 8) col (range 8)]
                  (get-in piece-locations [row col] "_"))]
    (->> board
         (partition 8)
         (map #(with-newline (str/join " " %)))
         (str/join ""))))

(defn can-attack [pieces]
  (let [{[white-row white-col] :w [black-row black-col] :b} pieces]
    (or (= white-row black-row)
        (= white-col black-col)
        ;; row-difference = col-difference => on same diagnonal
        (= (Math/abs (- black-row white-row)) (Math/abs (- black-col white-col))))))
