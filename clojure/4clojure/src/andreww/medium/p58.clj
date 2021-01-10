(ns andreww.medium.p58)

(def __
  (fn [& fns]
    (if (empty? fns)
      identity
      (fn [& args]
        (loop [data (apply (last fns) args)
               remaining (rest (reverse fns))]
          (if-let [f (first remaining)]
            (recur
              (f data)
              (rest remaining))
            data))))))

(comment

  (= [3 2 1] ((__ rest reverse) [1 2 3 4]))

  (= 5 ((__ (partial + 3) second) [1 2 3 4]))

  (= "HELLO" ((__ #(.toUpperCase %) #(apply str %) take) 5 "hello world"))

  )

;; Other solutions

;; austintaylor
(fn [& fs]
  (fn [& xs]
    (first (reduce #(vector (apply %2 %1)) xs (reverse fs)))))

;; nikelandjelo
;; note multiple args to apply
(fn comp2 [& fns]
             (if (= 1 (count fns))
               (first fns)
               (fn [& args]
                 (println args)
                 ((first fns) (apply (apply comp2 (rest fns)) args)))))

;; kohyama
(fn [& fs]
  (let [[rf & rfs] (reverse fs)]
    (fn [& args]
      (reduce #(%2 %) (apply rf args) rfs))))
