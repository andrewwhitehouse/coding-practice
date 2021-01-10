(ns andreww.easy.p21)

(def __ (fn [coll n] (->> coll (drop n) first)))

(comment

  (= (__ '(4 5 6 7) 2) 6)

  (= (__ [:a :b :c] 0) :a)

  (= (__ [1 2 3 4] 1) 2)

  (= (__ '([1 2] [3 4] [5 6]) 2) [5 6])

  )

