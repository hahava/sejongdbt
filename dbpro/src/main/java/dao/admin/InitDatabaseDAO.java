package dao.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import oracle.connect.OracleJDBCManager;

public class InitDatabaseDAO implements DAO {
	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();
		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	private InitDatabaseDAO() {
	}

	private static InitDatabaseDAO instance = new InitDatabaseDAO();

	public static InitDatabaseDAO getInstance() {
		return instance;
	}

	@Override
	// 데이터베이스를 초기화하는 메서드이다.
	public void list() {
		// TODO Auto-generated method stub
		String[] tableName = { "MOVIE", "MYUSER", "RAT", "PAYMENT", "MOVIE_PAYMENT", "SNACK_INFO", "MYUSER_SNACK_ORDER",
				"EMPLOYEE_TASK", "EMPLOYEE", "AD", "MOVIE_AD", "ACTOR", "MOVIE_ACTOR", "PAYMENT_STATICS" };
		String[] query = new String[14];
		String[] sequence = { "MOVIE_PAYMENT_INC", "EMPLOYEE_INC", "MOVIE_AD_INC", "SNACK_ORDER_INC" };

		int resultnum;

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		CallableStatement cstm = null;
		String insertQuery[];
		String temp = "INSERT INTO MOVIE VALUES('M1','광해','이영희',15,'역사','2017-05-11','2017-05-30');"
				+ "INSERT INTO MOVIE VALUES('M2','아가씨','박찬욱',19,'로맨스','2016-05-01','2016-08-26');"
				+ "INSERT INTO MOVIE VALUES('M3','친절한 금자씨','이민기',15,'스릴러','2017-05-15','2017-07-23');"
				+ "INSERT INTO MOVIE VALUES('M4','뺑덕엄마','유민준',12,'가족','2017-05-15','2017-08-01');"
				+ "INSERT INTO MOVIE VALUES('M5','그대를 사랑합니다','이준형',15,'로맨스','2017-05-05','2017-06-26');"
				+ "INSERT INTO MOVIE VALUES('M6','게임회사 직원들','이하린',12,'우정','2017-05-10','2017-05-26');"
				+ "INSERT INTO MOVIE VALUES('M7','박물관은 죽어있다','구민석',7,'미스터리','2017-05-01','2017-09-26');"
				+ "INSERT INTO MOVIE VALUES('M8','대추나무 사랑 열렸네','이민호',12,'가족','2017-04-01','2017-05-30');"
				+ "INSERT INTO MOVIE VALUES('M9','어느날 그가 죽었다','박진호',19,'스릴러','2017-05-02','2017-06-15');"
				+ "INSERT INTO MOVIE VALUES('M10','말할 수 없는 비밀','XIA MIN',15,'애니','2017-03-01','2017-06-10');"
				+ "INSERT INTO MOVIE VALUES('M11','겟 아웃','조던 필레',15,'스릴러','2017-05-17','2017-06-10');"
				+ "INSERT INTO MOVIE VALUES('M12','보스 베이비','톰 맥그리스',7,'애니','2017-05-03','2017-06-19');"
				+ "INSERT INTO MYUSER VALUES('eeww95','이가경','loveDB','1995-03-15','01022445959','eeww12@naver.com');"
				+ "INSERT INTO MYUSER VALUES('ghgh5216','정예지','moomoo1','1996-05-17','0211543518','ghgh5216@naver.com');"
				+ "INSERT INTO MYUSER VALUES('hahava','이하린','loveJava123','1992-02-15','01088554411','hahava@gmail.com');"
				+ "INSERT INTO MYUSER VALUES('ghc22','차광훈','ghc1313','1996-01-01','01055443322','ghc18@gmail.com');"
				+ "INSERT INTO MYUSER VALUES('kmono','김헌호','kimono31','1997-05-18','01088720032','kimo@hanmail.net');"
				+ "INSERT INTO MYUSER VALUES('minhokim','김민호','kimminho','1998-08-11','01055840044','kimkimwow@hanmail.net');"
				+ "INSERT INTO MYUSER VALUES('jayjay','jaydonaldkim','jaykay123','1990-01-20','01088515155','jaykay@naver.com');"
				+ "INSERT INTO MYUSER VALUES('miomio12','김미오','miomio233','1997-02-03','01094996155','miomio233@yahoo.com');"
				+ "INSERT INTO MYUSER VALUES('taobaoluv','황지원','lovechina','2000-01-23','01088446655','keyboardwant@yahoo.com');"
				+ "INSERT INTO MYUSER VALUES('prettyyeye','이예지','leeyazi','1993=02=01','01073374932','prettyyeye@naver.com');"
				+ "INSERT INTO MYUSER VALUES('bighead','왕대갈','head3247','1992-05-01','01039328849','bigbighead@naver.com');"
				+ "INSERT INTO MYUSER VALUES('admin','관리자','root123','2099-12-31','0234082255','admin@sejong.ac.kr');"
				+ "INSERT INTO RAT VALUES('eeww95','M1',5,default);"
				+ "INSERT INTO RAT VALUES('ghgh5216','M4',5,'개인적으로 영화를 별로 안좋아하는데, 이번 건 좋았습니다.');"
				+ "INSERT INTO RAT VALUES('hahava','M4',5,'어머니에 대한 사랑이 느껴지는 영화였습니다.');"
				+ "INSERT INTO RAT VALUES('jayjay','M4',2,'nonono');"
				+ "INSERT INTO RAT VALUES('kmono','M6',5,default);"
				+ "INSERT INTO RAT VALUES('hahava','M11',3,'너무 무서워요...');"
				+ "INSERT INTO RAT VALUES('ghgh5216','M11',5,default);"
				+ "INSERT INTO RAT VALUES('prettyyeye','M11',5,default);"
				+ "INSERT INTO RAT VALUES('bighead','M12',5,'너무 귀엽다 귀여워~!');"
				+ "INSERT INTO RAT VALUES('ghc22','M4',5,'오늘 어머니에게 사랑한다고 말씀드릴래요');"
				+ "INSERT INTO RAT VALUES('minhokim','M11',5,'오늘은 엄마랑 자야지!');"
				+ "INSERT INTO RAT VALUES('bighead','M7',2,'정말 영화가 죽어버렸네요.');"
				+ "INSERT INTO PAYMENT VALUES('P1','체크카드');" + "INSERT INTO PAYMENT VALUES('P2','무통장입금');"
				+ "INSERT INTO PAYMENT VALUES('P3','신용카드');" + "INSERT INTO PAYMENT VALUES('P4','문화상품권');"
				+ "INSERT INTO PAYMENT VALUES('P5','해피포인트');" + "INSERT INTO PAYMENT VALUES('P6','백화점상품권');"
				+ "INSERT INTO PAYMENT VALUES('P7','나라사랑');" + "INSERT INTO PAYMENT VALUES('P8','현금결제');"
				+ "INSERT INTO PAYMENT VALUES('P9','YES24 포인트');" + "INSERT INTO PAYMENT VALUES('P10','전체통장입금');"
				+ "INSERT INTO PAYMENT VALUES('P11','PAYCO');" + "INSERT INTO PAYMENT VALUES('P12','핸드폰결제');"
				+ "INSERT INTO MOVIE_PAYMENT VALUES(MOVIE_PAYMENT_INC.NEXTVAL, 'eeww95','M1','P1','2017-05-15');"
				+ "INSERT INTO MOVIE_PAYMENT VALUES(MOVIE_PAYMENT_INC.NEXTVAL, 'ghgh5216','M2','P2','2017-05-15');"
				+ "INSERT INTO MOVIE_PAYMENT VALUES(MOVIE_PAYMENT_INC.NEXTVAL, 'kmono','M3','P1','2017-05-15');"
				+ "INSERT INTO MOVIE_PAYMENT VALUES(MOVIE_PAYMENT_INC.NEXTVAL, 'minhokim','M4','P1','2017-05-15');"
				+ "INSERT INTO MOVIE_PAYMENT VALUES(MOVIE_PAYMENT_INC.NEXTVAL, 'taobaoluv','M5','P1','2017-05-15');"
				+ "INSERT INTO MOVIE_PAYMENT VALUES(MOVIE_PAYMENT_INC.NEXTVAL, 'miomio12','M6','P1','2017-05-15');"
				+ "INSERT INTO MOVIE_PAYMENT VALUES(MOVIE_PAYMENT_INC.NEXTVAL, 'ghgh5216','M7','P1','2017-05-15');"
				+ "INSERT INTO MOVIE_PAYMENT VALUES(MOVIE_PAYMENT_INC.NEXTVAL, 'eeww95','M8','P1','2017-05-15');"
				+ "INSERT INTO MOVIE_PAYMENT VALUES(MOVIE_PAYMENT_INC.NEXTVAL, 'eeww95','M9','P1','2017-05-15');"
				+ "INSERT INTO MOVIE_PAYMENT VALUES(MOVIE_PAYMENT_INC.NEXTVAL, 'eeww95','M10','P1','2017-05-15');"
				+ "INSERT INTO MOVIE_PAYMENT VALUES(MOVIE_PAYMENT_INC.NEXTVAL, 'bighead','M11','P11','2017-05-17');"
				+ "INSERT INTO MOVIE_PAYMENT VALUES(MOVIE_PAYMENT_INC.NEXTVAL, 'prettyyeye','M12','P12','2017-05-16');"
				+ "INSERT INTO SNACK_INFO VALUES ('S1','버터팝콘','담백한 버터와 고소한 팝콘의 맛을 느껴보세요!',600, 8000);"
				+ "INSERT INTO SNACK_INFO VALUES ('S2','카라멜팝콘','달콤한 카라멜과 고소한 팝콘의 맛을 느껴보세요!',700, 9000);"
				+ "INSERT INTO SNACK_INFO VALUES ('S3','반반팝콘','입안에서 퍼지는 팝콘들의 대환장쇼!',650, 8500);"
				+ "INSERT INTO SNACK_INFO VALUES ('S4','콜라','코카콜라',250, 1000);"
				+ "INSERT INTO SNACK_INFO VALUES ('S5','스프라이트','스프라이트',230, 1000);"
				+ "INSERT INTO SNACK_INFO VALUES ('S6','아메리카노','에티오피아산 원두로 손수 내린 아메리카노',30, 3500);"
				+ "INSERT INTO SNACK_INFO VALUES ('S7','직화구이 오징어','국산 오징어와 특제 소스의 조합',500, 5000);"
				+ "INSERT INTO SNACK_INFO VALUES ('S8','물','제주 삼다수',0, 1200);"
				+ "INSERT INTO SNACK_INFO VALUES ('S9','치킨너겟 20개','하림 닭으로 바삭하게 구워낸 너겟',550, 6000);"
				+ "INSERT INTO SNACK_INFO VALUES ('S10','세트메뉴1)팝콘+음료1','팝콘과 음료를 자유롭게 선택하세요',900, 9000);"
				+ "INSERT INTO SNACK_INFO VALUES ('S11','세트메뉴2)치킨너겟+오징어+음료1','짭짤하고 짭짤한 어른의 맛',1000, 12000);"
				+ "INSERT INTO SNACK_INFO VALUES ('S12','세트메뉴3)치킨너겟+콜라','치콜이 대세',800, 6500);"
				+ "INSERT INTO MYUSER_SNACK_ORDER VALUES(SNACK_ORDER_INC.NEXTVAL,'ghgh5216','S1','2017-05-09');"
				+ "INSERT INTO MYUSER_SNACK_ORDER VALUES(SNACK_ORDER_INC.NEXTVAL,'hahava','S2','2017-05-10');"
				+ "INSERT INTO MYUSER_SNACK_ORDER VALUES(SNACK_ORDER_INC.NEXTVAL,'jayjay','S2','2017-05-10');"
				+ "INSERT INTO MYUSER_SNACK_ORDER VALUES(SNACK_ORDER_INC.NEXTVAL,'miomio12','S3','2017-05-10');"
				+ "INSERT INTO MYUSER_SNACK_ORDER VALUES(SNACK_ORDER_INC.NEXTVAL,'prettyyeye','S4','2017-05-11');"
				+ "INSERT INTO MYUSER_SNACK_ORDER VALUES(SNACK_ORDER_INC.NEXTVAL,'bighead','S5','2017-05-11');"
				+ "INSERT INTO MYUSER_SNACK_ORDER VALUES(SNACK_ORDER_INC.NEXTVAL,'minhokim','S6','2017-05-12');"
				+ "INSERT INTO MYUSER_SNACK_ORDER VALUES(SNACK_ORDER_INC.NEXTVAL,'eeww95','S7','2017-05-13');"
				+ "INSERT INTO MYUSER_SNACK_ORDER VALUES(SNACK_ORDER_INC.NEXTVAL,'ghc22','S8','2017-05-13');"
				+ "INSERT INTO MYUSER_SNACK_ORDER VALUES(SNACK_ORDER_INC.NEXTVAL,'taobaoluv','S8','2017-05-14');"
				+ "INSERT INTO MYUSER_SNACK_ORDER VALUES(SNACK_ORDER_INC.NEXTVAL,'ghc22','S9','2017-05-14');"
				+ "INSERT INTO MYUSER_SNACK_ORDER VALUES(SNACK_ORDER_INC.NEXTVAL,'ghgh5216','S10','2017-05-17');"
				+ "INSERT INTO EMPLOYEE_TASK VALUES('사장', '영화사 대표',10000);"
				+ "INSERT INTO EMPLOYEE_TASK VALUES('재무관리', '영화사 재무담당',5500);"
				+ "INSERT INTO EMPLOYEE_TASK VALUES('비품담당', '간식부터 장비까지 물품담당',4000);"
				+ "INSERT INTO EMPLOYEE_TASK VALUES('배급담당', '배급사 계약담당',7000);"
				+ "INSERT INTO EMPLOYEE_TASK VALUES('홍보담당', '홍보관련 업무',3500);"
				+ "INSERT INTO EMPLOYEE_TASK VALUES('인사담당', '직원들의 인사관련 업무',4500);"
				+ "INSERT INTO EMPLOYEE_TASK VALUES('일반직원',  '손님응대 및 매표관련 업무',2000);"
				+ "INSERT INTO EMPLOYEE_TASK VALUES('IT팀', '홈페이지 관리 및 서버운영', 8000);"
				+ "INSERT INTO EMPLOYEE_TASK VALUES('청소용역', '영화사 청결 담당업무',2800);"
				+ "INSERT INTO EMPLOYEE_TASK VALUES('매니저', '각종 결제업무, 응대업무',3000);"
				+ "INSERT INTO EMPLOYEE_TASK VALUES('경호팀', '치안과 건물보호 업무',3000);"
				+ "INSERT INTO EMPLOYEE_TASK VALUES('법무팀', '회사 내 외부 법률소송 담당',7000);"
				+ "INSERT INTO EMPLOYEE VALUES(EMPLOYEE_INC.NEXTVAL,'황알찬',50,'남','사장','2000-01-01',default);"
				+ "INSERT INTO EMPLOYEE VALUES(EMPLOYEE_INC.NEXTVAL,'이민혁',32,'남','재무관리','2010-01-01',default);"
				+ "INSERT INTO EMPLOYEE VALUES(EMPLOYEE_INC.NEXTVAL,'지현우',25,'남','비품담당','2008-01-01',default);"
				+ "INSERT INTO EMPLOYEE VALUES(EMPLOYEE_INC.NEXTVAL,'최진리',37,'남','청소용역','2015-04-01',3);"
				+ "INSERT INTO EMPLOYEE VALUES(EMPLOYEE_INC.NEXTVAL,'전소미',22,'여','IT팀','2011-01-01',7);"
				+ "INSERT INTO EMPLOYEE VALUES(EMPLOYEE_INC.NEXTVAL,'박경리',28,'여','매니저','2012-01-01',2);"
				+ "INSERT INTO EMPLOYEE VALUES(EMPLOYEE_INC.NEXTVAL,'문현아',31,'여','인사담당','2013-01-01',default);"
				+ "INSERT INTO EMPLOYEE VALUES(EMPLOYEE_INC.NEXTVAL,'최수영',32,'여','홍보담당','2010-01-01',default);"
				+ "INSERT INTO EMPLOYEE VALUES(EMPLOYEE_INC.NEXTVAL,'이순규',28,'여','배급담당','2016-01-01',4);"
				+ "INSERT INTO EMPLOYEE VALUES(EMPLOYEE_INC.NEXTVAL,'황미영',27,'여','일반직원','2017-01-01',1);"
				+ "INSERT INTO EMPLOYEE VALUES(EMPLOYEE_INC.NEXTVAL,'권혜원',27,'여','법무팀','2016-03-01',default);"
				+ "INSERT INTO EMPLOYEE VALUES(EMPLOYEE_INC.NEXTVAL,'김수현',28,'남','경호팀','2010-04-01',2);"
				+ "INSERT INTO AD VALUES('강한남자, 헛개차','광동제약','2017-01-01',500000);"
				+ "INSERT INTO AD VALUES('새로운 터치감 ! 마제스터치 키보드','필코','2017-03-01',800000);"
				+ "INSERT INTO AD VALUES('탈모엔? 마이녹실!','서울제약','2017-04-01',500000);"
				+ "INSERT INTO AD VALUES('숙취엔 여명 808','여명','2016-01-01',1000000);"
				+ "INSERT INTO AD VALUES('CHANEL NO.5 PERFUME','CHANEL','2016-12-01',800000);"
				+ "INSERT INTO AD VALUES('KT - 아빠와 특별한 추억을','KT','2017-02-01',400000);"
				+ "INSERT INTO AD VALUES('페레로 로쉐','롯데제과','2017-01-01',500000);"
				+ "INSERT INTO AD VALUES('AWS - 새로운 클라우드의 혁신을 맛보세요','AMAZON','2015-11-01',5500000);"
				+ "INSERT INTO AD VALUES('앞뒤가 똑같은 대리운전','1577대리운전','2017-01-01',500000);"
				+ "INSERT INTO AD VALUES('행복한 척추','올바른자세','2017-03-01',70000);"
				+ "INSERT INTO AD VALUES('기호 1번 문재인','대통령됨','2017-05-11',50000);"
				+ "INSERT INTO AD VALUES('푸푸리 향긋한 뒷처리','푸푸리','2017-02-10',60000);"
				+ "INSERT INTO AD VALUES('간 때문이야','동일제약','2016-03-12',100000);"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL, 'M1','강한남자, 헛개차');"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL,'M1','새로운 터치감 ! 마제스터치 키보드');"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL,'M1','탈모엔? 마이녹실!');"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL,'M1','행복한 척추');"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL,'M2','행복한 척추');"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL,'M2','숙취엔 여명 808');"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL,'M2','KT - 아빠와 특별한 추억을');"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL,'M2','앞뒤가 똑같은 대리운전');"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL,'M3','AWS - 새로운 클라우드의 혁신을 맛보세요');"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL,'M4','AWS - 새로운 클라우드의 혁신을 맛보세요');"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL,'M5','탈모엔? 마이녹실!');"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL,'M6','페레로 로쉐');"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL,'M7','탈모엔? 마이녹실!');"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL,'M8','새로운 터치감 ! 마제스터치 키보드');"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL,'M9','CHANEL NO.5 PERFUME');"
				+ "INSERT INTO MOVIE_AD VALUES(MOVIE_AD_INC.NEXTVAL,'M10','새로운 터치감 ! 마제스터치 키보드');"
				+ "INSERT INTO ACTOR VALUES('A1','이지원','1980-02-12','여');"
				+ "INSERT INTO ACTOR VALUES('A2','김민찬','1990-02-13','남');"
				+ "INSERT INTO ACTOR VALUES('A3', '이혜림','1997-01-01','여');"
				+ "INSERT INTO ACTOR VALUES('A4','황민아','1930-01-02','여');"
				+ "INSERT INTO ACTOR VALUES('A5','구지원','1980-02-12','여');"
				+ "INSERT INTO ACTOR VALUES('A6','김민오','1990-02-13','남');"
				+ "INSERT INTO ACTOR VALUES('A7', '김영희','1997-01-01','여');"
				+ "INSERT INTO ACTOR VALUES('A8','박철수','1930-01-02','여');"
				+ "INSERT INTO ACTOR VALUES('A9','김여옥','1980-02-12','여');"
				+ "INSERT INTO ACTOR VALUES('A10','김민','1990-02-13','남');"
				+ "INSERT INTO ACTOR VALUES('A11', '선우정아','1997-01-01','여');"
				+ "INSERT INTO ACTOR VALUES('A12','이다니엘','1930-01-02','여');"
				+ "INSERT INTO ACTOR VALUES('A13','황지수','1980-02-12','여');"
				+ "INSERT INTO ACTOR VALUES('A14','김원효','1990-02-13','남');"
				+ "INSERT INTO ACTOR VALUES('A15', '이찬울','1997-01-01','여');"
				+ "INSERT INTO ACTOR VALUES('A16','황지용','1930-01-02','여');"
				+ "INSERT INTO ACTOR VALUES('A17','이다연','1980-02-12','여');"
				+ "INSERT INTO ACTOR VALUES('A18','김다민','1990-02-13','남');"
				+ "INSERT INTO ACTOR VALUES('A19', '이다빈','1997-01-01','여');"
				+ "INSERT INTO ACTOR VALUES('A20','박지성','1930-01-02','여');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A1','M1','주연');" + "INSERT INTO MOVIE_ACTOR VALUES('A2','M1','조연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A3','M2','주연');" + "INSERT INTO MOVIE_ACTOR VALUES('A4','M2','조연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A5','M3','주연');" + "INSERT INTO MOVIE_ACTOR VALUES('A6','M3','조연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A7','M4','주연');" + "INSERT INTO MOVIE_ACTOR VALUES('A8','M4','조연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A9','M5','주연');" + "INSERT INTO MOVIE_ACTOR VALUES('A10','M5','조연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A11','M6','주연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A12','M6','조연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A13','M7','주연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A14','M7','조연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A15','M8','주연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A16','M8','조연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A17','M9','주연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A18','M9','조연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A19','M10','주연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A20','M10','조연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A5','M11','조연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A1','M11','주연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A2','M11','조연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A20','M12','주연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A19','M12','주연');"
				+ "INSERT INTO MOVIE_ACTOR VALUES('A18','M12','조연');"
				+ "INSERT INTO PAYMENT_STATICS VALUES('2017-05-02',5000000,2200,2000);"
				+ "INSERT INTO PAYMENT_STATICS VALUES('2017-05-03',3600000,1200,1000);"
				+ "INSERT INTO PAYMENT_STATICS VALUES('2017-05-04',7900000,3000,2000);"
				+ "INSERT INTO PAYMENT_STATICS VALUES('2017-05-05',9000000,4000,4000);"
				+ "INSERT INTO PAYMENT_STATICS VALUES('2017-05-06',4000000,3300,3300);"
				+ "INSERT INTO PAYMENT_STATICS VALUES('2017-05-07',2000000,900,900);"
				+ "INSERT INTO PAYMENT_STATICS VALUES('2017-05-08',4300000,1800,1800);"
				+ "INSERT INTO PAYMENT_STATICS VALUES('2017-05-09',6600000,2000,2000);"
				+ "INSERT INTO PAYMENT_STATICS VALUES('2017-05-10',8900000,2300,2300);"
				+ "INSERT INTO PAYMENT_STATICS VALUES('2017-05-11',2900000,3400,3400);"
				+ "INSERT INTO PAYMENT_STATICS VALUES('2017-05-12',7500000,2200,2200);"
				+ "INSERT INTO PAYMENT_STATICS VALUES('2017-05-13',3300000,4400,4400);";
		insertQuery = temp.split(";");

		try {
			for (int i = 0; i < tableName.length; i++) {
				query[i] = "DELETE FROM " + tableName[i];
				pstm = conn.prepareStatement(query[i]);
				resultnum = pstm.executeUpdate();
				System.out.println("데이터를 모두 삭제했습니다..");
			}

			pstm = conn.prepareStatement("COMMIT");
			pstm.executeUpdate();

			// 회원번호등과 같은 경우, 시퀀스를 사용하여 insert 될 때마다 오토로 숫자를 증가시켜주는데, 초기화의 경우 이 시퀀스를 초기화해주어야 한다.
			// 이런 역할을 하는 프로시저콜을 호출해서 초기화를 완료한다.
			for (int i = 0; i < sequence.length; i++) {
				cstm = conn.prepareCall("{call reset_sequence(?)}");
				cstm.setString(1, sequence[i]);
				cstm.executeUpdate();
			}

			for (String s : insertQuery) {
				pstm = conn.prepareStatement(s);
				pstm.executeUpdate();
			}
			pstm = conn.prepareStatement("COMMIT");
			pstm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("실행도중 오류가 발생했습니다. 관리자에게 문의하세요.");
			System.out.println(e.getSQLState());
			e.printStackTrace();
		}
		try {
			cstm.close();

			pstm.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
