package dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dto.admin.EmployeeDTO;
import dto.admin.EmployeeTaskDTO;
import oracle.connect.OracleJDBCManager;

public class EmployeeDAO implements DAO {

	private EmployeeDAO() {

	}

	private static EmployeeDAO instance = new EmployeeDAO();

	public static EmployeeDAO getInstance() {
		return instance;
	}

	private Connection getConnection() {
		OracleJDBCManager manager = new OracleJDBCManager();
		String oracleId = "s15010924";
		String passwd = "s15010924";
		int port = 1521;
		manager.registerOracleJDBCDriver();
		Connection conn = manager.connect(oracleId, passwd, port);
		return conn;
	}

	public void list() {

		ArrayList<EmployeeDTO> arrayList = new ArrayList<>();

		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query = "select * from EMPLOYEE";

		try {
			pstm = conn.prepareStatement(query);
			result = pstm.executeQuery();
			while (result.next()) {
				arrayList.add(new EmployeeDTO(result.getInt("EMPLOYEE_NUM"), result.getString("EMPLOYEE_NAME"),
						result.getInt("EMPLOYEE_AGE"), result.getString("EMPLOYEE_GENDER"),
						result.getString("EMPLOYEE_ROLE"), result.getDate("EMPLOYEE_HIREDATE"),
						result.getInt("EMPLOYEE_PUN_CNT")));
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}

		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i).toString());
		}
	}
	public void employeeSearchMenu() {
		int selectMenu;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("환영합니다! 직원정보관리메뉴입니다.");
		System.out.println("1. 직원 정보 검색");
		System.out.println("2. 나이 기반 연봉 평균");
		System.out.println("3. 근태직원 조회");
		System.out.println("4. 뒤로");
		
		selectMenu=sc.nextInt();
		switch(selectMenu) {
		case 1:
			employeeSearch_allInfo();
			break;
		case 2:
			employeeSearch_age_sal();
			break;
		case 3:
			employeeSearch_pun_mem();
			break;
		case 4:
			return;
		default :
			System.out.println("잘못된 번호를  입력하셨습니다.");
		}
	}
	
	public void employeeSearch_allInfo() {
		String employeeName;
		Scanner sc=new Scanner(System.in);
		System.out.println("직원을 검색합니다. 이름을 입력하세요.(*를 누르면 모든 정보를 출력합니다.)");
		employeeName=sc.nextLine();
		ArrayList<EmployeeDTO> employeeDTO=new ArrayList<EmployeeDTO>();
		ArrayList<EmployeeTaskDTO> employeetaskDTO=new ArrayList<EmployeeTaskDTO>();
				String query="select employee_num,employee_name,employee_age,employee_gender,emp.employee_role,employee_hiredate,employee_pun_cnt,employee_task_con,employee_task_sal from employee emp, employee_task emp_t "
				+ "where emp.employee_role=emp_t.employee_role";
		if(employeeName.compareTo("*")!=0) {
			query+=" and emp.employee_name='"+employeeName+"'";
		}
			
		
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		
		try {
			pstm=conn.prepareStatement(query);
			result=pstm.executeQuery();

			while(result.next()) {

				employeeDTO.add(new EmployeeDTO(result.getInt("employee_num"),
						result.getString("employee_name"),
						result.getInt("employee_age"),
						result.getString("employee_gender"),
						result.getString("employee_role"),
						result.getDate("employee_hiredate"),
						result.getInt("employee_pun_cnt")));
				
				employeetaskDTO.add(new EmployeeTaskDTO(result.getString("employee_role"),result.getString("employee_task_con"),
						result.getInt("employee_task_sal")));
		
			}
			if(employeeDTO.isEmpty()) {
				System.out.println("해당하는 직원이 없습니다.");
			}
			
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < employeeDTO.size(); i++) {
			System.out.println(employeeDTO.get(i).toString());
			System.out.println(employeetaskDTO.get(i).toString());
		}
		
	}
	
	
	public void employeeSearch_age_sal() {
		int agemin;
		int agemax;
		Scanner sc=new Scanner(System.in);
		System.out.println("나이 기반 연봉 평균입니다. 최소 나이와 최대 나이를 입력하세요.");
		agemin=sc.nextInt();
		agemax=sc.nextInt();
		String query;

		query="select count(*),avg(employee_task_sal) "
				+ "from employee_task "
				+ "where employee_role in (select employee_role from employee where employee_age between "+agemin+" and +"+agemax+")";
		


		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		try {
			pstm=conn.prepareStatement(query);
			result=pstm.executeQuery();
			
			if(result.next()) {
				System.out.print("count : "+result.getInt(1)+","+"\tavg : "+result.getInt(2)+"\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		}
		
	public void employeeSearch_pun_mem() {
		System.out.println("근태 직원을 출력합니다.");
		Connection conn = getConnection();
		PreparedStatement pstm = null;
		ResultSet result = null;
		String query="select employee_num, employee_name, employee_role, employee_pun_cnt "
				+ "from employee "
				+ "where employee_pun_cnt>0";
		
		try {
			pstm=conn.prepareStatement(query);
			result=pstm.executeQuery();
			while(result.next()) {
				System.out.println(result.getInt(1)+"\t"+result.getString(2)+"\t"+result.getString(3)+"\t"+result.getInt(4));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			result.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
		
	}

