package com.rain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rain.bean.AdminBean;
import com.rain.bean.BookBean;
import com.rain.util.DBUtil;

/**
 * 有关读者账号的连接数据库操作，登录验证，注册，修改账号，修改密码
 */
public class AdminDao {

	/**
	 * 登录验证功能，传入用户名和密码，在数据库中查找，如果找到了，返回true，没找到则返回false
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean Login_verify(String username, String password) {
		Connection conn = DBUtil.getConnectDb();//DBUtil.getConnectDb()：这是一个工具类方法，用于获取数据库连接
		PreparedStatement stm = null;
		ResultSet rs = null;
		//PreparedStatement：这是一个预处理语句对象，用于执行SQL查询。它可以帮助防止SQL注入攻击。
		//ResultSet：这是一个结果集对象，用于存储查询结果。
		String sql = "select * from admin where username='" + username + " 'and password='" + password + "'";
		try {
			stm = conn.prepareStatement(sql);//使用连接对象创建一个预处理语句
			rs = stm.executeQuery(); //执行查询，并将结果存储在ResultSet对象中
			if (rs.next()) {  //检查结果集中是否有下一条记录。如果有，表示找到了匹配的用户名和密码，返回true。
				return true;//如果next()返回true，这意味着结果集中至少有一条记录，即找到了匹配的用户名和密码。
				//如果next()返回false，这意味着结果集中没有记录，即没有找到匹配的用户名和密码。
			}
		} catch (SQLException e) { //捕获并处理SQLException异常，打印错误堆栈
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);  //无论查询成功与否，都执行资源关闭操作。
		}
		return false;
	}

	/**
	 * 注册账号的函数，传入账号，密码，姓名，邮箱，手机号，借阅天数，可借阅数
	 * 
	 * @param username
	 * @param password
	 * @param name
	 * @param email
	 * @param phone
	 * @param times
	 * @param lend_num
	 * @param max_num
	 */
	public void Register(String username, String password, String name, String email, String phone, int lend_num,
			int max_num) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "insert into admin(status,username,password,name,email,phone,lend_num,max_num) values(?,?,?,?,?,?,?,?)";
		int rs = 0;
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, 1);
			stm.setString(2, username);
			stm.setString(3, password);
			stm.setString(4, name);
			stm.setString(5, email);
			stm.setString(6, phone);
			stm.setInt(7, lend_num);
			stm.setInt(8, max_num);
			rs = stm.executeUpdate();
			//stm.setInt(1, 1);：将第一个参数（位置1）的值设置为整数 1。
			//stm.setString(2, username);：将第二个参数（位置2）的值设置为字符串 username。
			//stm.setString(3, password);：将第三个参数（位置3）的值设置为字符串 password。
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 新增管理员账号，传入账号，密码，姓名，邮箱，手机号
	 * 
	 * @param username
	 * @param password
	 * @param name
	 * @param email
	 * @param phone
	 * @param times
	
	 */
	public void Register2(String username, String password, String name, String email, String phone) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "insert into admin(status,username,password,name,email,phone) values(?,?,?,?,?,?)";
		int rs = 0;
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, 2);
			stm.setString(2, username);
			stm.setString(3, password);
			stm.setString(4, name);
			stm.setString(5, email);
			stm.setString(6, phone);
			rs = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据传入的账号，密码，来查找对应的读者信息，返回一个AdminBean类型，
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public AdminBean getAdminInfo(String username, String password) { //AdminBean：返回类型，表示这个方法将返回一个AdminBean类型的对象。
		// TODO Auto-generated method stub
		AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where username= '"+username+"' and password= '"+password+"'";
		
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));//从当前行的ResultSet对象rs中检索username列的值。
				//将检索到的值作为参数传递给AdminBean对象adminbean的setUsername方法
				//setUsername方法将这个值赋给adminbean对象的username属性
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return adminbean;
	}

	/**
	 * 获取全部用户的信息，其中sql语句中的status=1，表示只查找读者，不显示管理员的
	 * 
	 * @return
	 */
	public ArrayList<AdminBean> get_ListInfo() {
		ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>(); //返回一个AdminBean对象列表
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where status=1";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				AdminBean adminbean = new AdminBean();
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
				tag_Array.add(adminbean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag_Array;
	}

	/**
	 * 获取全部用户的信息，其中sql语句中的status=2，表示只查找管理员，不显示读者的
	 * 
	 * @return
	 */
	public ArrayList<AdminBean> get_ListInfo2() {
		ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where status=2";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				AdminBean adminbean = new AdminBean();
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				tag_Array.add(adminbean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag_Array;
	}
	/**
	 * 获取全部用户的信息并排序，其中sql语句中的status=1，表示只查找读者，不显示管理员的
	 * 
	 * @return
	 */
	public ArrayList<AdminBean> get_ListInfo3() {
		ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where status=1 order by times desc";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				AdminBean adminbean = new AdminBean();
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
				tag_Array.add(adminbean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag_Array;
	}

	/**
	 * 根据传入的aid，查找到对应的读者的全部信息，返回一个AdminBean类型的数据
	 * 
	 * @param aid
	 * @return
	 */
	public AdminBean get_AidInfo(int aid) {
		AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where aid=" + aid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return adminbean;
	}

	/**
	 * 根据传入的aid，查找到对应的读者的全部信息，返回一个AdminBean类型的数据，与上一个相似，只是aid的类型为String
	 * 
	 * @param aid
	 * @return
	 */
	public AdminBean get_AidInfo2(String aid) {
		AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where aid=" + aid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setName(rs.getString("name"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setEmail(rs.getString("email"));
				adminbean.setPhone(rs.getString("phone"));
				adminbean.setTimes(rs.getInt("times"));
				adminbean.setStatus(rs.getInt("status"));
				adminbean.setLend_num(rs.getInt("lend_num"));
				adminbean.setMax_num(rs.getInt("max_num"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return adminbean;
	}

	/**
	 * 修改读者的信息
	 */
	public void updateUser(int aid, String username, String password, String name, String email, String phone,
			int lend_num, int max_num) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "update admin set username=?,name=?,email=?,phone=?,password=?,lend_num=?,max_num=? where aid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, name);
			stm.setString(3, email);
			stm.setString(4, phone);
			stm.setString(5, password);
			stm.setInt(6, lend_num);
			stm.setInt(7, max_num);
			stm.setInt(8, aid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改管理员的信息
	 */
	public void updateAdmin(int aid, String username, String password, String name, String email, String phone) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "update admin set username=?,name=?,email=?,phone=?,password=? where aid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, name);
			stm.setString(3, email);
			stm.setString(4, phone);
			stm.setString(5, password);
			stm.setInt(6, aid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除用户的信息，根据传入的aid作为条件
	 * 
	 * @param aid
	 */
	public void deleteUser(int aid) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "delete from admin where aid=?";//aid=?是一个参数化查询的占位符，表示aid的值将在预处理语句中通过参数设置
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql); 
			stm.setInt(1, aid); //设置SQL语句中第一个参数（位置1）的值为aid。这里1对应于SQL语句中?的位置，aid是要删除的用户的管理员ID。
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除管理员的信息，根据传入的aid作为条件
	 * 
	 * @param aid
	 */
	public void deleteAdmin(int aid) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "delete from admin where aid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, aid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查找用户，根据输入的名称，使用like进行模糊查询，然后返回一个ArrayList数组类型
	 * 
	 * @param name
	 * @return
	 */
	public ArrayList<AdminBean> getLikeList(String name) {
		// TODO Auto-generated method stub
		ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();//创建一个AdminBean对象的ArrayList，用于存储所有查询到的用户信息
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where name like '%" + name + "%' or username like '%" + name + "%' or aid like '%" + name + "%'";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) { //用while循环遍历ResultSet对象rs中的所有记录
				AdminBean tag = new AdminBean();//对于每条记录，创建一个新的AdminBean对象，
				//使用ResultSet对象rs的方法（如getInt、getString）获取每列的值
				tag.setAid(rs.getInt("aid"));
				tag.setStatus(rs.getInt("status"));
				tag.setUsername(rs.getString("username"));
				tag.setName(rs.getString("name"));
				tag.setPassword(rs.getString("password"));
				tag.setEmail(rs.getString("email"));
				tag.setPhone(rs.getString("phone"));
				tag.setTimes(rs.getInt("times"));
				tag.setLend_num(rs.getInt("lend_num"));
				tag.setMax_num(rs.getInt("max_num"));
				tag_Array.add(tag);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag_Array;
	}
}
