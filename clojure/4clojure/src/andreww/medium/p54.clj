(ns andreww.medium.p54)

(def __
  (fn [x coll]
    (let [my-partition (fn next [acc remaining]
                  (if (seq remaining)
                    (lazy-seq (next (conj acc (take x remaining)) (drop x remaining)))
                    acc))]
      (->> (my-partition '() coll)
           (filter #(= x (count %)))
           reverse)))
  )

(comment

  (= (__ 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))

  (= (__ 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))

  (= (__ 3 (range 8)) '((0 1 2) (3 4 5)))

  )

