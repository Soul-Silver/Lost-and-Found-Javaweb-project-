package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

public class lost_li extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ʼ��
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=test";
		String user = "sa";
		String pwd = "160510111xyj";
		//�������ݿ����st
		Statement st;
		//��ȡ����
		
		try {
			Class.forName(driverName);
			try {
				Connection conn = DriverManager.getConnection(url,user,pwd);
				//������ѯ����
				st = conn.createStatement();
				System.out.println("����lost�ɹ�");
				
				//���ӳɹ���ʼ��ѯ
				
				List<Map> list = new ArrayList<Map>();//����list�������ڴ���map�ļ�ֵ�Լ���
				//д��ѯ���
				String sql = "select * from lost";
				//��ȡ���
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()){
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String kind = rs.getString("kind");
					String description = rs.getString("description");
					String time = rs.getString("time");
					String location = rs.getString("location");
					//����Map
					Map map = new HashMap();
					map.put("id", id);
					map.put("name", name);
					map.put("kind", kind);
					map.put("description", description);
					map.put("time", time);
					map.put("location", location);

					list.add(map);//�����map�������list
				}
				JSONArray jsonArray = JSONArray.fromObject(list);
				System.out.print(jsonArray);
				PrintWriter out = response.getWriter();
				out.println(jsonArray);
				out.close();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("����lost����2");
		}
	
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("����lost����1");
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
