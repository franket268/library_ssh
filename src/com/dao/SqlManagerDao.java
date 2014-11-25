package com.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.PropertyResourceBundle;



public class SqlManagerDao
{
	private static SqlManagerDao manager = null; // ��̬��Ա������֧�ֵ�̬ģʽ
	private PropertyResourceBundle bundle; // ������Դ�ļ�
	private static String jdbcDrive = null; // JDBC��������
	private String DBhost = ""; // ���ݿ�������ַ
	private String DBname = ""; // ���ݿ���
	private String DBprot = ""; // ���ݿ�˿�
	private String DBuser = ""; // ���ݿ��û���
	private String DBpasswd = ""; // ���ݿ�����
	private String strcon = null; // �����ַ���

	private Connection conn = null; // ���Ӷ���
	private PreparedStatement pstm = null;
	private Statement sqlStmt=null;
	private CallableStatement cstm = null;

	/**
	 * ˽�й��캯��,����ʵ����
	 * 
	 * @throws IOException
	 */
	private SqlManagerDao() throws IOException
	{
		// ��ȡ�����ļ�
		bundle = new PropertyResourceBundle(SqlManagerDao.class
				.getResourceAsStream("Config.properties"));
		this.DBhost = getString("DBhost"); // ��ȡ������
		this.DBname = getString("DBname"); // ��ȡ�û���
		this.DBprot = getString("DBport"); // ��ȡ�˿�
		this.DBuser = getString("DBuser"); // ��ȡ�û�
		this.DBpasswd = getString("DBpassword"); // ��ȡ����
		// ����mysql���ݿ����������������ַ�
		jdbcDrive = "com.mysql.jdbc.Driver";
		strcon = "jdbc:mysql://" + DBhost + ":" + DBprot + "/" + DBname;
	}

	/**
	 * ��ȡ�����ļ��е�ֵ
	 * 
	 * @param key
	 *            �����ļ���key
	 * @return key��Ӧ��ֵ
	 */
	private String getString(String key)
	{
		return this.bundle.getString(key);
	}

	/**
	 * ��̬ģʽ��ȡʵ��
	 * 
	 * @return SqlManager����
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public static SqlManagerDao createInstance() throws IOException, ClassNotFoundException
	{
		if (manager == null)
		{
			manager = new SqlManagerDao();
			manager.initDB();
		}
		return manager;
	}

	/**
	 * ��ʼ�����Ӳ�������ָ����DBType����
	 * 
	 * @throws ClassNotFoundException
	 */
	public void initDB() throws ClassNotFoundException
	{
		Class.forName(jdbcDrive);
	}

	/**
	 * �������ݿ�
	 * 
	 * @throws SQLException
	 */
	public void connectDB() throws SQLException
	{
		conn = DriverManager.getConnection(strcon, DBuser, DBpasswd); // ��ȡ����
		conn.setAutoCommit(false); // �����Զ��ύΪfalse
	}

	/**
	 * �Ͽ����ݿ�
	 * 
	 * @throws SQLException
	 */
	public void closeDB() throws SQLException
	{
		if (pstm != null)
		{
			pstm.close();
		}
		if (cstm != null)
		{
			cstm.close();
		}
		if (conn != null)
		{
			conn.close();
		}
	}

	/**
	 * ����PrepareStatement������Sql����еĲ���
	 * 
	 * @param sql
	 *            sql���
	 * @param params
	 *            �����б�
	 * @throws SQLException
	 */
	private void setPrepareStatementParams(String sql, Object[] params)
			throws SQLException
	{
		pstm = conn.prepareStatement(sql); // ��ȡ����
		if (params != null)
		{
			for (int i = 0; i < params.length; i++) // ���������б�������
			{
				pstm.setObject(i + 1, params[i]);
			}
		}
	}

	/**
	 * ִ�в�ѯ
	 * 
	 * @param sql
	 *            sql���
	 * @param params
	 *            �����б�
	 * @return ����ResultSet���͵Ĳ�ѯ���
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String sql, Object[] params)
			throws SQLException
	{ // ִ�в�ѯ���ݿ�ӿ�
		ResultSet rs = null;
		manager.setPrepareStatementParams(sql, params); // ������
		rs = pstm.executeQuery(); // ִ�в�ѯ����
		return rs;
	}
	
	
	//����������ִ�в�ѯ
	public ResultSet executeQuery2(String sql)
			throws SQLException
	{ 
		ResultSet rs = null;
		sqlStmt=(Statement) conn.createStatement();
		rs = sqlStmt.executeQuery(sql); // ִ�в�ѯ����
		return rs;
	}
	

	/**
	 * �������ݿ����
	 * 
	 * @param sql
	 *            sql���
	 * @param params
	 *            �����б�
	 * @return ִ�в����Ľ��
	 * @throws SQLException
	 */
	public boolean executeUpdate(String sql, Object[] params)
			throws SQLException 
	{
		boolean result = false;
		manager.setPrepareStatementParams(sql, params); // ������
		pstm.executeUpdate(); // ִ�и���
		manager.commitChange();
		result = true;
		return result;
	}

	/**
	 * �ύ��Ϣ�����ݿ�
	 * 
	 * @throws SQLException
	 */
	private void commitChange() throws SQLException
	{
		conn.commit();
	}
}

