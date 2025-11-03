package com.kt.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kt.domain.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {
	private final JdbcTemplate jdbcTemplate;
	public void save(User user) {
		var sql = """
			INSERT INTO MEMBER (
													id,
													loginId,
													password,
													name,
													birthday,
													mobile,
													email,
													gender,
													createdAt,
													updatedAt
													) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
			""";
		jdbcTemplate.update(
			sql,
			user.getId(),
			user.getLoginId(),
			user.getPassword(),
			user.getName(),
			user.getBirthday(),
			user.getMobile(),
			user.getEmail(),
			user.getGender() != null ? user.getGender().name() : null,
			user.getCreatedAt(),
			user.getUpdatedAt()
		);
	}

	public Long selectMaxId() {
		var sql = "SELECT MAX(id) FROM MEMBER";
		var maxId = jdbcTemplate.queryForObject(sql, Long.class);

		return maxId == null ? 0L : maxId;
	}

	public boolean existsByLoginId(String loginId) {
		var sql = "SELECT EXISTS (SELECT id FROM MEMBER WHERE loginId = ?)";

		return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, loginId));
	}

}


// 크게 세가지 정도 아이디 중복 체크 방법
// 1. count해서 0보다 큰지 체크 -> 별로 좋아보이지는 않음
// db에 만약 유저가 3000만명 있다면 -> 중복체크 한 번 할때마다, 3천만개의 데이터를 모두 살펴봐야함 (full-scan)
// 2. unique 제약조건 걸어서 예외처리 -> 별로 좋아보이지는 않음
// 유니크키 에러 (DataViolation Exception)
// 3. exists로 존재 여부 체크 -> boolean으로 값 존재 여부를 바로 알 수 있음