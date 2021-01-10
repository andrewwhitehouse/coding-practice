(ns andreww.hard.p79)

(defn paths [rows]
  (reduce (fn [acc ])))


(def __ (fn [rows]
          (reduce
            (fn [acc row-idx]
              )
            [(ffirst rows) 0]
            (range 1 (count rows)))))

(= (__ [     [3]
        [2 4]
        [1 9 3]
        [9 9 2 4]
        [4 6 6 7 8]
        [5 7 3 5 1 4]])
   (+ 3 4 3 2 7 1)
   20)


(= (__ [   [1]
        [2 4]
        [5 1 4]
        [2 3 4 5]])
   (+ 1 2 1 3)
   7)
