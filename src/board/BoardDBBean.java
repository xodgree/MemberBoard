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
//DB ���� ���� ����.
	public Connection getConnection(){
	//   ����Ÿ��      ����             
	   Connection con = null;			
	   try {
		   //DB�� URL,����ID,PW
	      String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";	
	      String dbUser = "scott";
	      String dbPass = "tiger";
	      
	      // ���÷���(reflection) ���� �ε��� ���� �ڵ�
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      
	      // DB URL,����, ��й�ȣ�� ������ DB�� �����մϴ�. ���� ����� con�� �����մϴ�.
	      con = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
	      System.out.println("����Ǿ����ϴ�.");
	      
	   // ����(Exception)�� �߻��ϸ� � �������� �ľ��ϱ� ���� �ڵ尡 ���⿡ ���ϴ�.
	   }catch(Exception e) {
	      e.printStackTrace();
	      System.out.println(e.getMessage());
	   }
	   // Exception�� �߻����� �ʾҴٸ� ������ �����Ͽ����ϴ�.
	   // ���� ������ return�մϴ�.
	   return con;
	   }

	//Count ���� �޼ҵ�
	public int getDataCount() {
		String sql = "select nvl(count(*),0) from users";
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {count =rs.getInt(1);}
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close(con,rs,pstmt);
		}
		return count;
	}
	
	// ������ �������� �޼ҵ�
	public List articleList() {
		// Connection, PreparedStatement, ResultSet �� 
		// DB�� �����Ͽ� �۾��ϱ� ���� �ʿ��� ���۷��� ������ �����մϴ�.
		// ���� 3������ DB �۾��� �ʿ��� �⺻ ��ҵ��Դϴ�.
		Connection conn = null;	//Ŀ�ؼ� ����.
		PreparedStatement pstmt = null; //������ ����.
		ResultSet rs = null;	//select ���� ������ DB�� ��û�� ����� ��.
		
		// Article�� ������ ArrayList ������ �����մϴ�.
		List articleList = null;	
		String sql = "";		//���� �ۼ� ����
		
		try {
			conn = getConnection();		//conn�� getConnection�޼ҵ带 ����. ��, con�� ����.
			// PreparedStatement�� ������ ������ ����ϴ�.
			sql = "select num,email,name,passwd,regdate from users";		//users���̺� ��ü ��ȸ ���� ����.
			
			// Connection�� ������ ����ϰ� PreparedStatement�� �ֽ��ϴ�.
			pstmt = conn.prepareStatement(sql); //pstmt = sql ������ ���� 
			
			// PreparedStatement�� ��ϵ� ������ �����մϴ�.
			// Select �����̹Ƿ� ResultSet���� �� ����� ����ϴ�.
			rs = pstmt.executeQuery();
			
			// ResultSet�� �����͸� Ȯ���մϴ�
			
			// ResultSet.next()�� ó�� ����Ǹ� ResultSet�� ������ �ִ� ù��° �����͸� ����ŵ�ϴ�.
			// ���� ResultSet�� ������ �ִ� �����Ͱ� ���ٸ� null�� return�մϴ�.
			if(rs.next()) {
				articleList = new ArrayList();	//null�̾��� articleList�� �迭�� ������. article���� ������� list.
				
				do {
					BoardDataBean article = new BoardDataBean(); //���̺� ������ �� �������� ��ü ����.
					
					// ResultSet���� �ʿ��� �����͸� column �̸����� ���� ����ϴ�.
					// ���� �����ʹ� Model�� BoardDataBean ��ü�� setter�� �̿��ؼ� ���� �������ݴϴ�.
					article.setNum(rs.getInt("num"));
					//System.out.println("���ڳѾ�쳪"+rs.getInt("num"));
					article.setEmail(rs.getString("email"));
					article.setName(rs.getString("name"));
					article.setPasswd(rs.getString("passwd"));
					article.setRegdate(rs.getTimestamp("regdate"));
					
					// ResultSet�� ������, ��, Article �����Ͱ� BoardDataBean ��ü�� ���޵Ǿ����ϴ�.
					// �տ��� ����� �� BoardDataBean ��ü�� �����ϱ� ���ؼ� �����Ͽ��� ArrayList�� �����մϴ�.
					articleList.add(article);
					//System.out.println(article);
				}while(rs.next());
				// �� ������ ResultSet�� ���̻� �����Ͱ� ���������� ����˴ϴ�.
			}	
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//close(conn,rs,pstmt);
			
			//System.out.println(articleList);
		}
		return articleList;
		
		
	}
	
	//ȸ�� ���, ������ ���� �޼ҵ�
	//	���� Ÿ�� void, BoardDataBean type�� article�� �Ű������� ����
	public void insertArticle(BoardDataBean member) {
		//������ ������ sql ���� ����
		String sql ="";
		//db�� Ŀ�ؼ� ����.
		Connection con = getConnection();
		//������ ����
		PreparedStatement pstmt = null;
		//����� ������
		ResultSet rs = null;
		int number =0;
		try {
			//Ŀ�ؼ��� �̿��Ͽ� ������ ����. ���� ���� => ������. ������ ����ϸ� num�� 1�� �ڵ� �����ǵ��� ��.
			pstmt = con.prepareStatement("select boardser.nextval from dual");
			rs = pstmt.executeQuery(); //���� �������� rs�� ����.
			
			//ResultSet.next()�� ó�� ����Ǹ� ResultSet�� ������ �ִ� ù��° �����͸� ����ŵ�ϴ�.
			if(rs.next())
				//�����Ͱ� ������ number�� +1�ȴ�.
				number = rs.getInt(1)+1;
			// ���� ResultSet�� ������ �ִ� �����Ͱ� ���ٸ�  number�� 1�̴�.
			else
				number = 1;
		
		
		
		//������ ���� sql���� �ۼ�
		sql = "insert into users(num,name,email,passwd,regdate)"+"values(?,?,?,?,sysdate)";
		
		pstmt = con.prepareStatement(sql);
		//������ �� ������ �̿�
		pstmt.setInt(1, number);
	
		pstmt.setString(2, member.getName());
		
		pstmt.setString(3, member.getEmail());
		
		pstmt.setString(4, member.getPasswd());
		
		
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









