package com.my.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
public class Board {
	private int level;  //글레벨 : 1-원글, 2-답글, 3-답글의 답글, 4-답글의 답글의 답글
	private int boardNo;  //게시글 번호
	private int boardParentNo;
	
	private String boardTitle;
	private String boardContent;
	@JsonFormat(pattern = "yy/MM/dd", timezone = "Asia/Seoul")
	private Date boardDt;
	
	@NonNull //null로 설정(ex: setBoardId(null) 또는 new Board(~~~, null, ~~)되면 NullPointerException 예외발생시킴 
	private String boardId;  // ? private Customer boardC;
	
	private int boardViewcount;
}
