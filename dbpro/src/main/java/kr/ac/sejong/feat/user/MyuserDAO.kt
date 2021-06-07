package kr.ac.sejong.feat.user

import kr.ac.sejong.config.JDBCManager
import java.util.function.Consumer

class MyuserDAO private constructor() {

    companion object {
        // 만들어진 인스턴스 리턴
        @JvmStatic
        var instance: MyuserDAO? = null
            get() {
                if (field == null) {
                    field = MyuserDAO()
                }
                return field
            }
            private set
    }

    fun selectUsers(): List<MyuserDTO> {
        val query = """
            SELECT 
            	MYUSER_ID, 
            	MYUSER_NAME, 
            	MYUSER_PW, 
            	MYUSER_BIRTH, 
            	MYUSER_PHONE, 
            	MYUSER_EMAIL 
            FROM 
            	MYUSER
        """

        return JDBCManager
            .getInstance()
            .queryForList(query, MyuserDTO::class.java)
    }

    // 로그인 한 유저의 정보만을 출력한다.
    fun selectUserInfo(id: String?) {
        val query = """
            SELECT 
            	MYUSER_ID, 
            	MYUSER_NAME, 
            	MYUSER_PW, 
            	MYUSER_BIRTH, 
            	MYUSER_PHONE, 
            	MYUSER_EMAIL 
            FROM 
            	MYUSER 
            WHERE 
            	MYUSER_ID = $id
        """

        JDBCManager
            .getInstance()
            .queryForList(query, MyuserDTO::class.java)
            .forEach(Consumer { myuserDTO -> println(myuserDTO.toString()) })
    }

    // 로그인 메서드 id와 password를 입력받아 myuser테이블안에 저장되어 있는지 확인한다. 로그인 가능 여부를 확인 하는 메서드
    fun login(id: String?, pw: String?): Map<String, Any> {
        val query = """
            SELECT 
           	MYUSER_ID 
           FROM 
           	MYUSER 
           WHERE 
           	MYUSER_ID = $id
           AND 
           	MYUSER_PW = $pw 
       """

        return JDBCManager
            .getInstance()
            .queryForMap(query, arrayOf("MYUSER_ID"))
    }

}