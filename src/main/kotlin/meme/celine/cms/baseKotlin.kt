package meme.celine.cms

/*
Exemple pour la syntaxe

fun lenght(str: String):Int {
    var l = 0 // val comme const, val doit être le standard et var l'exception
    for(c in str){
        l += 1
    }
    return l // return str.lenght
}

fun add(a: Int, b: Int):Int = a + b // fonction très concise, 1 ligne

Sur une variable => faire Ctrl Shift P => il devine le type de la variable, sinon il faut typer

 */



fun runIf(predicate: Boolean, f: ()->Unit){
    //Unit (=void)
    if(predicate){
        f()
    }
}

fun runWithAnswer(f: (Int)->Unit){
    //Unit (=void)
    f(42)
}


fun main(){
    //println("Bonjour le monde!")
    runIf(true, {println("Je passe")})
    /* ou */ runIf(true) {println("Je passe")}

    var list = listOf(1,2,3)
    list.forEach(){
        println(it)
    }



    /* ou */ runWithAnswer { answer ->
        println("Answer is $answer, and its double is $(answer * 2)")
    }
    //ou par défaut it (pas besoinde déclarer la variable answer)




    //val p = Person("Céline", 20)






    val p1 = Person("Céline", 20)
    val p2 = Person("Céline", 20)

    println(p1 == p2) // => false




    val p = Person("Céline", 20)
    //println(p.description)



}

class I{
    fun method(){} // return a Unit
}
class J{}

open class A{} //type de function influe sur l'héritage

/*class C : A(),I, J{
    override fun method(){

    }
}*/

class Person(name: String, age: Int){
    /*
    val name: String
    val age: Int =
     */

    /*
    init{
        this.name = name
        this.age = age
    }
    */

    //on peut  se limiter à class Person(name: String, age: Int)

}


/*data class(private val name: String, private val age: Int){
    fun description() = "$name aged $age"
}*/

//data class(val name: String, val age: Int)
//fun Person.description() = "$name aged $age"