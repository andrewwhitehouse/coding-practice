(ns andreww.hard.p101)


(def __
  (fn [coll1 coll2]
    (let [[shorter longer] (if (<= (count coll1) (count coll2)) [coll1 coll2] [coll2 coll1])
          deletes (fn [v]
                    {:pre  [(vector? v)]}
                    (map
                      (fn [index]
                        (vec (concat (subvec v 0 index) (subvec v (inc index)))))
                      (range (count v))))
          count-differences (fn [coll1 coll2]
                             (->> (map (fn [a b] (= a b)) coll1 coll2)
                                  (filter false?)
                                  count))]
      (loop [deletions 0
             candidates (set [(vec longer)])]
        (if (contains? candidates (vec shorter))
          deletions
          (if (< (count shorter) (count (first candidates)))
            (recur
              (inc deletions)
              (set (mapcat deletes candidates)))
            (+ deletions (apply min (map (partial count-differences shorter) candidates))))))))
  )


(comment

  (= (__ "kitten" "sitting") 3)

  (= (__ "closure" "clojure") (__ "clojure" "closure") 1)

  (= (__ "xyx" "xyyyx") 2)

  (= (__ "" "123456") 6)

  (= (__ "Clojure" "Clojure") (__ "" "") (__ [] []) 0)

  (= (__ [1 2 3 4] [0 2 3 4 5]) 2)

  (= (__ '(:a :b :c :d) '(:a :d)) 2)

  (= (__ "ttttattttctg" "tcaaccctaccat") 10)

  (= (__ "gaattctaatctc" "caaacaaaaaattt") 9)

  )


(= (__ (map inc (take 3 (drop 2 [2 5 4 1 3 6])))) ;; '( 5 2 4 )
   (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (__))
   11)

