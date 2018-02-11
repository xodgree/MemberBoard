package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDBBean {
	private static BoardDBBean instance = new BoardDBBean();
	private BoardDBBean() {
		
	}
	public static BoardDBBean getInstance() {
		return instance;
	}
	
//DB connection
//DB 계정 정보 포함.
	public Connection getConnection(){
	//   리턴타입      변수             
	   Connection con = null;			
	   try {
		   //DB의 URL,계정ID,PW
	      String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";	
	      String dbUser = "scott";
	      String dbPass = "tiger";
	      
	      // 리플렌션(reflection) 동적 로딩에 대한 코드
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      
	      // DB URL,계정, 비밀번호를 가지고 DB에 접속합니다. 접속 결과를 con에 저장합니다.
	      con = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
	      System.out.println("연결되었습니다.");
	      
	   // 예외(Exception)가 발생하면 어떤 문제인지 파악하기 위한 코드가 여기에 들어갑니다.
	   }catch(Exception e) {
	      e.printStackTrace();
	      System.out.println(e.getMessage());
	   }
	   // Exception이 발생하지 않았다면 무사히 접속하였습니다.
	   // 접속 정보를 return합니다.
	   return con;
	   }

	// 데이터 가져오는 메소드
	public List getArticles() {
		// Connection, PreparedStatement, ResultSet 등 
		// DB에 접속하여 작업하기 위해 필요한 레퍼런스 변수를 선언합니다.
		// 위의 3가지는 DB 작업에 필요한 기본 요소들입니다.
		Connection conn = null;	//커넥션 정보.
		PreparedStatement pstmt = null; //쿼리를 담음.
		ResultSet rs = null;	//select 쿼리 날리면 DB에 요청한 결과를 줌.
		
		// Article을 저장할 ArrayList 변수를 선언합니다.
		List articleList = null;	
		String sql = "";		//쿼리 작성 변수
		
		try {
			conn = getConnection();		//conn에 getConnection메소드를 넣음. 즉, con을 넣음.
			// PreparedStatement로 실행할 쿼리를 만듭니다.
			sql = "select * from users";		//users테이블 전체 조회 쿼리 날림.
			
			// Connection에 쿼리를 등록하고 PreparedStatement에 넣습니다.
			pstmt = conn.prepareStatement(sql); //pstmt = sql 쿼리를 담음 
			
			// PreparedStatement로 등록된 쿼리를 실행합니다.
			// Select 쿼리이므로 ResultSet으로 그 결과를 얻습니다.
			rs = pstmt.executeQuery();
			
			// ResultSet의 데이터를 확인합니다
			// ResultSet.next()는 처음 실행되면 ResultSet이 가지고 있는 첫번째 데이터를 가리킵니다.
			// 만약 ResultSet이 가지고 있는 데이터가 없다면 null을 return합니다.
			if(rs.next()) {
				articleList = new ArrayList();	//null이었던 articleList에 배열을 생성함. article들을 담기위한 list.
				
				do {
					BoardDataBean article = new BoardDataBean(); //테이블 변수에 값 설정위해 객체 생성.
					
					// ResultSet에서 필요한 데이터를 column 이름으로 각각 얻습니다.
					// 얻은 데이터는 Model인 BoardDataBean 객체의 setter를 이용해서 값을 설정해줍니다.
					article.setNum(rs.getInt("num"));
					article.setEmail(rs.getString("email"));
					article.setName(rs.getString("name"));
					article.setPasswd(rs.getString("passwd"));
					article.setRegdt(rs.getTimestamp("regdt"));
					
					// ResultSet의 데이터, 즉, Article 데이터가 BoardDataBean 객체로 전달되었습니다.
					// 앞에서 만들어 둔 BoardDataBean 객체를 보관하기 위해서 생성하였던 ArrayList에 저장합니다.
					articleList.add(article);
					//System.out.println(article);
				}while(rs.next());
				// 이 과정은 ResultSet에 더이상 데이터가 없을때까지 진행됩니다.
			}	
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//close(conn,rs,pstmt);
			
			//System.out.println(articleList);
		}
		return articleList;
		
		
	}
	
	//회원 등록, 데이터 삽입 메소드
	//	리턴 타입 void, BoardDataBean type의 article을 매개변수로 받음
	public void insertArticle(BoardDataBean article) {
		//쿼리를 저장할 sql 변수 선언
		String sql ="";
		//db와 커넥션 해줌.
		Connection con = getConnection();
		//쿼리를 담음
		PreparedStatement pstmt = null;
		//결과를 보여줌
		ResultSet rs = null;
		int number =0;
		try {
			//커넥션을 이용하여 쿼리를 담음. 쿼리 내용 => 시퀀스. 데이터 등록하면 num이 1씩 자동 증가되도록 함.
			pstmt = con.prepareStatement("select boardser.nextval from dual");
			rs = pstmt.executeQuery(); //쿼리 내보낸걸 rs에 담음.
			
			//ResultSet.next()는 처음 실행되면 ResultSet이 가지고 있는 첫번째 데이터를 가리킵니다.
			if(rs.next())
				//데이터가 있으면 number은 +1된다.
				number = rs.getInt(1)+1;
			// 만약 ResultSet이 가지고 있는 데이터가 없다면  number은 1이다.
			else
				number = 1;
			int num = article.getNum();
		
		
		//데이터 삽입 sql쿼리 작성
		sql = "insert into users(num,name,email,passwd,regdt)"+"values(?,?,?,?,sysdate)";
		
		pstmt = con.prepareStatement(sql);
		//위에서 쓴 시퀀스 이용
		pstmt.setInt(1, number);
		pstmt.setString(2, article.getName());
		pstmt.setString(3, article.getEmail());
		pstmt.setString(4, article.getPasswd());
		
		pstmt.executeUpdate();
		
	}catch(SQLException e1) {
			e1.printStackTrace();
		}finally {
			close(con,rs,pstmt);
		}
	
}

	private void close(Connection con, ResultSet rs, PreparedStatement pstmt) {
		// TODO Auto-generated method stub
		if(rs != null)
			try {
				rs.close();
			}catch(SQLException ex) {}
		if(pstmt !=null) try {pstmt.close();
			}catch(SQLException ex) {}
		if(con !=null)
			try {
				con.close();
			}catch(SQLException ex) {
		}
	}
}









