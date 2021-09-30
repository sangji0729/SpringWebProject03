package com.sangji0729.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sangji0729.common.CommandMap;

@Repository("testDAO")
public class TestDAO extends AbstractDAO {

	public List<Map<String, Object>> boardList(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("test.boardList", map);
	}

	public Map<String, Object> detail(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("test.detail", map);
	}

	public int delete(Map<String, Object> map) {
		return delete("test.delete", map);
	}

	public int totalCount(Map<String, Object> map) {
		// return (int) selectOne("test.totalCount", map).get("totalCount");
		return Integer.parseInt(String.valueOf(selectOne("test.totalCount", map).get("totalCount")));
	}

}
