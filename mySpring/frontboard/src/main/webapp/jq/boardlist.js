$(function () {
  let loginedId = 'id1'; //테스트용

  //function showList(pageNo){
  function showList(url/*, data*/) {
    $.ajax({
      // url: '/backboard/boardlist',
      // data: 'currentPage=' + pageNo,
      url: url,
      method: 'get',
      // data: data,
      success: function (jsonObj) {
        if (jsonObj.status == 1) {
          let pageBeanObj = jsonObj.t;

          //게시글 div를 원본으로 한다. 복제본만든다
          let $board = $('div.board').first();
          //나머지 게시글 div는 삭제한다
          $('div.board').not($board).remove();

          let $boardParent = $board.parent();
          $(pageBeanObj.list).each(function (index, board) {
            let $boardCopy = $board.clone();//복제본
            $boardCopy.find("div.board_no").html(board.boardNo);
            $boardCopy.find("div.board_parent_no").html(board.boardParentNo);
            $boardCopy.find("div.board_title").html(board.boardTitle);
            $boardCopy.find("div.board_dt").html(board.boardDt);
            $boardCopy.find("div.board_id").html(board.boardId);
            $boardCopy.find("div.board_viewcount").html(board.boardViewcount);
            $boardCopy.find("div.arrow").addClass("down");
            $boardParent.append($boardCopy);
          });

          let $pagegroup = $('div.pagegroup')
          let $pagegroupHtml = '';
          if (pageBeanObj.startPage > 1) {
            $pagegroupHtml += '<span class="prev">PREV</span>';
          }
          for (let i = pageBeanObj.startPage; i <= pageBeanObj.endPage; i++) {
            $pagegroupHtml += '&nbsp;&nbsp;';
            if (pageBeanObj.currentPage == i) { //현재페이지인 경우 <span>태그 안만듦
              // $pagegroupHtml += i;
              $pagegroupHtml += '<span class="disabled">' + i + '</span>';
            } else {
              $pagegroupHtml += '<span>' + i + '</span>';
            }
          }
          if (pageBeanObj.endPage < pageBeanObj.totalPage) {
            $pagegroupHtml += '&nbsp;&nbsp;';
            $pagegroupHtml += '<span class="next">NEXT</span>';
          }

          $pagegroup.html($pagegroupHtml);
        } else {
          alert(jsonObj.msg);
        }
      },
      error: function (jqXHR) {
        alert("에러:" + jqXHR.status);
      }
    });
  }

  //---페이지 로드되자 마자 게시글1페이지 검색 START---
  // showList('/backboard/boardlist', 'currentPage=1');
  showList('http://localhost:9997/backboardboot/board/list'/*, 'currentPage=1'*/);
  // showList('http://192.168.0.21:9997/backboardboot/board/list');
  //---페이지 로드되자 마자 게시글1페이지 검색 END---

  //---페이지 그룹의 페이지를 클릭 START---
  $('div.pagegroup').on('click', 'span:not(.disabled)', function () {
    let pageNo = 1;
    // if (!$(this).hasClass('disabled')) {
      if ($(this).hasClass('prev')) {
        pageNo = parseInt($(this).next().html()) - 1;
      } else if ($(this).hasClass('next')) {
        pageNo = parseInt($(this).prev().html()) + 1;
      } else {
        pageNo = parseInt($(this).html());
      }
      // alert("보려는 페이지번호: " + pageNo);
      let word = $('div.search>div.searchInput>input[name=word]').val().trim();
      let url = '';
      // let data = '';
      if (word == '') {
        // url = '/backboard/boardlist';
        url = 'http://localhost:9997/backboardboot/board/list/' + pageNo;
        // data = 'currentPage=' + pageNo;
      } else {
        // url = '/backboard/searchboard';
        // data = 'currentPage=' + pageNo + '&word=' + word;
        url = 'http://localhost:9997/backboardboot/board/search/' + word + '/' + pageNo;
      }
      showList(url/*, data*/);
      return false;
    // }
  });
  //---페이지 그룹의 페이지를 클릭 END---

  //---검색 클릭 START---
  $('div.search>div.searchInput>a').click(function () {
    let word = $('div.search>div.searchInput>input[name=word]').val().trim();
    // let url = '/backboard/searchboard';
    // let data = 'currentPage=1&word=' + word;
    let url = 'http://localhost:9997/backboardboot/board/search/' + word;
    showList(url/*, data*/);
    return false;
  });
  //---검색 클릭 END---

 //---arrow화살표클릭 START---
 $('div.boardlist').on('click', 'div.board>div.cell>div.summary>div.arrow', function(){
    if($(this).hasClass('down')){
      let boardNo = $(this).siblings('div.board_no').html();
      let $viewCount = $(this).siblings('div.board_viewcount');
      let $detail = $(this).parents('div.cell').find('div.detail');
      let $boardContent = $detail.find('input.board_content');
      let $modifyNremove = $detail.find('div.modifyNremove');
      
      $.ajax({
        //url: '/backboard/viewboard',
        //url:  '/backboard/board/view' + boardNo,
        // url:  '/backboard/board/' + boardNo,
        url:  'http://localhost:9997/backboardboot/board/' + boardNo,
        method:'get',
        data : 'boardNo='+boardNo,
        success:function(jsonObj){
          if(jsonObj.status == 1){
            let board = jsonObj.t;
            $viewCount.html(board.boardViewcount);   
            if(loginedId == board.boardId){
              $boardContent.removeAttr('readonly');
              $boardContent.css('outline','auto');
              $modifyNremove.show();
            }else{
              $boardContent.attr('readonly', 'readonly');
              $boardContent.css('outline','none');
              $modifyNremove.hide();  
            }
            $boardContent.val(board.boardContent);
            $detail.show();
          }else{
            alert(jsonObj.msg);
          }
        },
        error:function(jqXHR){
          alert('에러:' + jqXHR.status);
        }
      });
      $(this).addClass('up');
      $(this).removeClass('down');
    }else if($(this).hasClass('up')){
      $(this).addClass('down');
      $(this).removeClass('up');
      $(this).parents('div.cell').find('div.detail').hide();
    }
  });
  //---arrow화살표클릭 END---

  //---수정버튼 클릭 START---
  $('div.boardlist').on('click', 'div.board div.modifyNremove>button.modify', function () {
    let boardNo = $(this).parents('div.cell').find('div.board_no').html()
    let boardContent = $(this).parents('div.cell').find('input.board_content').val().trim();    
    //글 내용 확인(유효성 검사)
    if(boardContent == ""){
      alert("글내용은 반드시 입력하세요");
      return false;
    }

    $.ajax({
      // "url": "/backboard/board/" + boardNo,
      "url": "http://localhost:9997/backboardboot/board/" + boardNo,
      "method": "PUT",
      "timeout": 0,
      "headers": {
        "Content-Type": "application/json"
      },
      "data": JSON.stringify({
        "boardNo": boardNo,
        "boardContent": boardContent
      }),
      success: function (jsonObj) {
        alert("수정성공");
      }, error: function (jqXHR, textStatus) {
        alert("수정 에러:" + jqXHR.status + ", jqXHR.responseText:" + jqXHR.responseText);
      }
    });
    return false;
  });
  //---수정버튼 클릭 END---

  //---삭제버튼 클릭 START---
  $('div.boardlist').on('click', 'div.board div.modifyNremove>button.remove', function () {
    alert("in 삭제");
    let boardNo = $(this).parents('div.cell').find('div.board_no').html()
    $.ajax({
      // "url": "/backboard/board/" + boardNo,
      "url": "http://localhost:9997/backboardboot/board/" + boardNo,
      "method": "DELETE",
      success: function () {
        location.href = "./boardlist.html";
      }, error: function (jqXHR, textStatus) {
        alert("삭제 에러:" + jqXHR.status + ", jqXHR.responseText:" + jqXHR.responseText);
      }
    });
    return false;
  });
  //---삭제버튼 클릭 END---

  //---답글저장버튼 클릭 START---
  $('div.boardlist').on('click', 'div.board div.reply button', function () {
    let boardParentNo = $(this).parents('div.cell').find('div.board_no').html()
    
    let boardTitle = $(this).siblings('input[name=board_title]').val()
    let boardContent = $(this).siblings('textarea[name=board_content]').val()

    alert("in 답글 부모글번호:" + boardParentNo+", boardTitle:" + boardTitle + ", boardContent:" + boardContent);

    $.ajax({
      // "url": "/backboard/board/reply/" + boardParentNo,
      "url": "http://localhost:9997/backboardboot/board/reply/" + boardParentNo,
      "method": "POST",
      "timeout": 0,
      "headers": {
        "Content-Type": "application/json"
      },
      "data": JSON.stringify({
        "boardTitle": boardTitle,
        "boardContent": boardContent
      }),
      success: function () {
        location.href = "./boardlist.html";
      }, error: function (jqXHR, textStatus) {
        alert("답글 에러:" + jqXHR.status + ", jqXHR.responseText:" + jqXHR.responseText);
      }
    });
    return false;    
  });
  //---답글저장버튼 클릭 END---

});

