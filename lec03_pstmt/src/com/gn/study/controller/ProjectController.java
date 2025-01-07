package com.gn.study.controller;

import java.util.List;

import com.gn.study.model.dao.ProjectDao;
import com.gn.study.model.vo.ProjectVo;

public class ProjectController {
	private ProjectDao pd = new ProjectDao(); // controller <- model.dao
	
	public int insertProject(String projectName, String managerName) {
		return pd.insertProject(projectName, managerName);
	}
	
	public List<ProjectVo> selectProjectAll() {
		return pd.selectProjectAll();
	}
	
	public List<ProjectVo> selectProjectAllByName(String projectName) {
		return pd.selectProjectAllByName(projectName);
	}
	
	public List<ProjectVo> selectProjectAllByManagerName(String managerName) {
		return pd.selectProjectAllByManagerName(managerName);
	}
	
	public int updateProjectOne(int projectNo, String projectName) {
		return pd.updateProjectOne(projectNo, projectName);
	}
	
	public int deleteProjectOne(int projectNo) {
		return pd.deleteProjectOne(projectNo);
	}
}
