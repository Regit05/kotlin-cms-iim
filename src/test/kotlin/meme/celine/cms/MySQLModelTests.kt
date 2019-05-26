/*package meme.celine.cms

import org.junit.jupiter.api.BeforeAll

class MySQLModelTests {


    val pool = ConnectionPool("jdbc:h2:mem:kotlin-cms;MODE-MySQL", "", "")

    @Before
    fun setUp(){
        pool.useConnection { connection ->

            connection.prepareStatement("""CODE SQL MODIFIE""").use { stmt ->
                stmt.execute()
            }

        }
    }

    @Test
    fun simpleTest(){


        val model = MysqlModel(pool)
        val article = model.getArticle(1)!!

        Assert.assertEquals(2, list.size)
        Assert.assertEquals("Un", list[0].title)
        Assert.assertEquals("Deux", list[1].title)

    }


    @After
    fun tearDown(){
        pool.useConnection { connection ->

            connection.prepareStatement("""DROP TABLE IF EXISTS 'articles'""").use { stmt ->
                stmt.execute()
            }

        }
    }



}
 */