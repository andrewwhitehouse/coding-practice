(ns andreww.easy.p27)

(def __ (fn [coll] (= (seq coll) (reverse coll))))

(comment

  (false? (__ '(1 2 3 4 5)))

  (true? (__ "racecar"))

  (true? (__ [:foo :bar :foo]))

  (true? (__ '(1 1 3 3 1 1)))

  (false? (__ '(:a :b :c)))

  )
