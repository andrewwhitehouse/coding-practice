(ns armstrong-numbers)

(defn long-pow [^long n pow]
  (reduce * (repeat pow n)))

(defn digits
  ([n] (if (zero? n) '(0) (digits n '())))
  ([n digs] (if (zero? n) digs (digits (quot n 10) (cons (rem n 10) digs)))))

(defn armstrong? [num]
  (let [digs (digits num)]
    (= num (apply + (map #(long-pow % (count digs)) digs)))))

(defn -main [& args]
  (println (armstrong? 21897142587612075)))
