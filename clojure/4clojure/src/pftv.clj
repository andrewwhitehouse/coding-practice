(ns pftv)

(defn set? [coll]
  (->>
    [(map :color coll)
     (map :number coll)
     (map :shape coll)
     (map :shading coll)]
    (map (comp count distinct))
    (every? #{1 3})))

(comment

  (false? (set? [{:color :purple :number 3 :shape :diamond :shading :full}
                    {:color :red    :number 3 :shape :diamond :shading :lines}
                    {:color :purple :number 3 :shape :diamond :shading :empty}]))

  (true? (set? [{:color :purple :number 3 :shape :diamond :shading :full}
                   {:color :red    :number 3 :shape :diamond :shading :lines}
                   {:color :green  :number 3 :shape :diamond :shading :empty}]))

  )
