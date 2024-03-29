package com.nanosim.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.nanosim.model.Patent;
import com.nanosim.util.ISqlHelper;

public class PatentDAO {

	ISqlHelper sqlHelper = ISqlHelper.Factory.getInstance();

	public List<Patent> getGroupPatents(long groupId) {
		List<Patent> patents = new ArrayList<Patent>();
		ResultSet rs = null;
		try {
			rs = sqlHelper
					.executeQuery(
							"SELECT A.*, B.name groupName, C.title research_title FROM patents A inner join groups B ON A.group_id = B.group_id inner join research_types C ON A.research_type_id = C.research_type_id WHERE A.group_id = ? ORDER BY submitted DESC",
							groupId);
			Patent p = null;
			while (rs.next()) {
				p = new Patent();

				p.setPatentId(rs.getInt("patent_id"));
				p.setGroupId(rs.getInt("group_id"));
				p.setGroupName(rs.getString("groupName"));
				p.setResearchTypeId(rs.getInt("research_type_id"));
				p.setResearchTitle(rs.getString("research_title"));
				p.setProposal(rs.getString("proposal"));
				p.setSubmitted(rs.getDate("submitted"));
				p.setApproved(rs.getString("approved"));
				p.setResponse(rs.getString("response"));
				patents.add(p);
			}
		} catch (Exception e) {
		} finally {
			if (rs != null)
				sqlHelper.close();
		}
		return patents;
	}

	public List<Patent> getApprovedPatents() {
		List<Patent> patents = new ArrayList<Patent>();
		ResultSet rs = null;
		try {
			rs = sqlHelper
					.executeQuery("SELECT A.*, B.name groupName, C.title research_title FROM patents A inner join groups B ON A.group_id = B.group_id inner join research_types C ON A.research_type_id = C.research_type_id where A.approved='y' ORDER BY submitted DESC");
			Patent p = null;
			while (rs.next()) {
				p = new Patent();

				p.setPatentId(rs.getInt("patent_id"));
				p.setGroupId(rs.getInt("group_id"));
				p.setGroupName(rs.getString("groupName"));
				p.setResearchTypeId(rs.getInt("research_type_id"));
				p.setResearchTitle(rs.getString("research_title"));
				p.setProposal(rs.getString("proposal"));
				p.setSubmitted(rs.getDate("submitted"));
				p.setApproved(rs.getString("approved"));
				p.setResponse(rs.getString("response"));
				patents.add(p);
			}
		} catch (Exception e) {
		} finally {
			if (rs != null)
				sqlHelper.close();
		}
		return patents;
	}

	public List<Patent> getNewPatents() {
		List<Patent> patents = new ArrayList<Patent>();
		ResultSet rs = null;
		try {
			rs = sqlHelper
					.executeQuery("SELECT A.*, B.name groupName, C.title research_title FROM patents A inner join groups B ON A.group_id = B.group_id inner join research_types C ON A.research_type_id = C.research_type_id where A.approved!='y' ORDER BY submitted DESC");
			Patent p = null;
			while (rs.next()) {
				p = new Patent();

				p.setPatentId(rs.getInt("patent_id"));
				p.setGroupId(rs.getInt("group_id"));
				p.setGroupName(rs.getString("groupName"));
				p.setResearchTypeId(rs.getInt("research_type_id"));
				p.setResearchTitle(rs.getString("research_title"));
				p.setProposal(rs.getString("proposal"));
				p.setSubmitted(rs.getDate("submitted"));
				p.setApproved(rs.getString("approved"));
				p.setResponse(rs.getString("response"));
				patents.add(p);
			}
		} catch (Exception e) {
		} finally {
			if (rs != null)
				sqlHelper.close();
		}
		return patents;
	}

	public int submitPatent(Patent item) {
		try {
			int retVal = sqlHelper
					.executeUpdate(
							"INSERT INTO patents (group_id, class_id, research_type_id, proposal, submitted, response) VALUES (?, ?, ?, ?, NOW(), ?)",
							item.getGroupId(), item.getClassId(), item
									.getResearchTypeId(), item.getProposal(),
							item.getResponse());
			return retVal;
		} catch (Exception e) {
			return -1;
		}
	}

	public int approvePatent(Patent item) {
		try {
			int retVal = sqlHelper
					.executeUpdate(
							"UPDATE patents SET approved = ?, response = ? WHERE patent_id = ?",
							item.getApproved(), item.getResponse(), item
									.getPatentId());
			return retVal;
		} catch (Exception e) {
			return -1;
		}
	}

	public boolean alreadyHasPatent(int researchTypeId) {
		ResultSet rs = null;
		try {
			rs = sqlHelper
					.executeQuery(
							"SELECT count(*) result_count FROM patents WHERE research_type_id = ?",
							researchTypeId);
			if (rs.next()) {
				return rs.getInt("result_count") > 0;
			}
			return false;
		} catch (Exception e) {
			return false;
		} finally {
			if (rs != null)
				sqlHelper.close();
		}
	}
}
