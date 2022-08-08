package com.my.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Component  // Board DTO가 잘 실행되는지 간단하게 테스트하기위해 설정

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@EqualsAndHashCode(of= {"boardNo"})  
//다른객체와 다른 혹은 같은 객체인지 Equals,Hash를 사용하지만, 모든 멤버변수를 쓸필요없이
//다른지 같은지 기준이 되는 boardNo 멤버변수만을 of= 으로 설정한다 
@ToString
//@Data // 위 어노테이셔들을 한번에 다 생성해줌, 하지만 of=로 세세한설정을 못하므로 별로 권장하지않음 

@Entity
@Table(name = "board_jpa")
@SequenceGenerator(name = "boardjpa_seq_generator",
				   sequenceName = "board_jpa_seq",
				   initialValue = 8, //샘플데이터가 7개있으므로 8부터 시작하도록 세팅
				   allocationSize = 1 //시퀀스 증가값
)
@DynamicInsert //insert할때 고정된 sql문을 쓰지않고 동적sql문을 쓰게함
@DynamicUpdate //update할때 set 컬럼을 제외한 다른 컬럼이 null값으로 바뀌는걸 방지
public class Board {
	@Transient //테이블에 컬럼생성 방지
	private int level;  //글레벨 : 1-원글, 2-답글, 3-답글의 답글, 4-답글의 답글의 답글
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
				    generator = "boardjpa_seq_generator") //@SequenceGenerator를 boardNo에 사용
	@Column(name = "board_no")
	private Long boardNo;  //게시글 번호
	
	@Column(name = "board_parent_no")
	@ColumnDefault(value = "-1") 
	// -1로 세팅한다는것은 DB상에 VALUE로 -1 한다는 의미지, 자바 엔터티상에서 
	// VALUE를 -1로 세팅한다는게 아님. 자바 엔터티상에서 VALUE는 자료형의 DEFAULT값을
	// 따르므로 board_parent_no의 자료형은 int, int의 default값은 0이다
	// 즉 default value 값을 -1로 세팅해도 자바 엔터티의 자료형 default값을 따름
	// 이를 해결하기위해 int라 쓰지않고 정수타입의 참조형인 Long을 써주면 default값은
	// DB의 default값을 따르게 됨
	private Long boardParentNo;
	
	@Column(name = "board_title")
	private String boardTitle;
	
	@Column(name = "board_content")
	private String boardContent;
	
	@JsonFormat(pattern = "yy/MM/dd", timezone = "Asia/Seoul")
	@Column(name = "board_dt")
	@ColumnDefault(value = "SYSDATE")
	private Date boardDt;
	
//	@NonNull //null로 설정(ex: setBoardId(null) 또는 new Board(~~~, null, ~~)되면 NullPointerException 예외발생시킴 
	@Column(name = "board_id")
	private String boardId;  // ? private Customer boardC;
	
	@Column(name = "board_viewcount")
	@ColumnDefault(value = "0")
	private Integer boardViewcount;
}