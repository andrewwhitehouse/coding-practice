(ns andreww.easy.p28)

(def __
  (comp
    reverse
    (fn my-flatten
      [coll]
      (loop [remaining coll
             acc '()]
        (if-let [elem (first remaining)]
          (if (coll? elem)
            (recur (rest remaining) (concat (my-flatten elem) acc))
            (recur (rest remaining) (cons elem acc)))
          acc))))
  )

(comment

  (= (__ '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))

  (= (__ '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))

  (= (__ '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))

  )
