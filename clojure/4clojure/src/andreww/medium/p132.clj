(ns andreww.medium.p132)

(defn __ [f ins coll] 
  (letfn [(my-ins [previous f ins coll]
            (if (empty? coll)
              coll
              (let [elem (first coll)]
                (concat 
                  (if (f previous elem) [ins elem] [elem])
                  (lazy-seq 
                    (my-ins elem f ins (rest coll)))))))]
    (if (first coll)
      (cons (first coll) (my-ins (first coll) f ins (rest coll)))
      '())))

(and
  (= '(1 :less 6 :less 7 4 3) (__ < :less [1 6 7 4 3]))
  (= '(2) (__ > :more [2]))
  (= [0 1 :x 2 :x 3 :x 4]  (__ #(and (pos? %) (< % %2)) :x (range 5)))
  (empty? (__ > :more ()))
  (= [0 1 :same 1 2 3 :same 5 8 13 :same 21]
     (take 12 (->> [0 1]
                   (iterate (fn [[a b]] [b (+ a b)]))
                   (map first) ; fibonacci numbers
                   (__ (fn [a b] ; both even or both odd
                         (= (mod a 2) (mod b 2)))
                       :same)))) )
