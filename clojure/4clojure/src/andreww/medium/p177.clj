(ns andreww.medium.p177)

(def __
  (fn [s]
    (loop [remaining s
           stack '()]
      (println remaining stack)
      (if-let [ch (first remaining)]
        (condp contains? ch
          #{\[ \( \{} (recur
                        (rest remaining)
                        (conj stack ch))
          #{\] \) \}} (if (not= ({\} \{ \) \( \] \[} ch) (peek stack))
                        false
                        (recur
                          (rest remaining)
                          (pop stack)))
          (recur (rest remaining) stack))
        (empty? stack))))
  )

(comment

  (__ "This string has no brackets.")

  (__ "class Test {
      public static void main(String[] args) {
        System.out.println(\"Hello world.\");
      }
    }")

  (not (__ "(start, end]"))


  (not (__ "())"))

  (not (__ "[ { ] } "))

  (__ "([]([(()){()}(()(()))(([[]]({}()))())]((((()()))))))")

  (not (__ "["))

  )
