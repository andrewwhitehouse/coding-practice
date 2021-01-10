(ns andreww.hard.p53)

(defn __ [coll]
  (->> 
    (reduce 
      (fn [acc n]
        (if (empty? acc)
          (conj acc [n])
          (let [last-seen (last (last acc))]
            (if (= (inc last-seen) n)
              (conj (vec (butlast acc)) (conj (last acc) n)) ;continuing sequence
              (conj acc [n]))))) ; new sequence
      []
      coll)
    (reduce #(if 
               (and 
                 (> (count %2) 1) 
                 (> (count %2) (count %1))) 
               %2 %1) [])))

(and
  (= (__ [1 0 1 2 3 0 4 5]) [0 1 2 3])
  (= (__ [5 6 1 3 2 7]) [5 6])
  (= (__ [2 3 3 4 5]) [3 4 5])
  (= (__ [7 6 5 4]) []) )
