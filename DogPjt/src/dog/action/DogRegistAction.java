package dog.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dog.svc.DogRegistService;
import dog.vo.ActionForward;
import dog.vo.DogBean;

public class DogRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward action = null;
		
		// 파일 업로드를 위한 업로드 정보 설정
		String saveFolder = "/images";		// 가상위치
		// 가상위치에 대한 실제폴더의 위치를 설정
		ServletContext context = request.getServletContext();	// 톰캣 객체를 들고옴
		String realFolder = context.getRealPath(saveFolder);
		
		// 파일 업로드 최대 크기
		int fileSize = 1024 * 1024 * 10;		// 최대 5MB까지 (단위 용량으로 표기)
		
		// 파일 업로드를 위한 MulipartRequest 객체 필요
		MultipartRequest multi = new MultipartRequest(
				request,
				realFolder,		 
				fileSize, 
				"UTF-8", 
				new DefaultFileRenamePolicy()  // 중복된 이름이 있을 때 처리
				);
		
		DogBean dogBean = new DogBean();
		dogBean.setKind(multi.getParameter("kind"));
		dogBean.setCountry(multi.getParameter("country"));
		dogBean.setPrice(Integer.parseInt(multi.getParameter("price")));
		dogBean.setHeight(Integer.parseInt(multi.getParameter("height")));
		dogBean.setWeight(Integer.parseInt(multi.getParameter("weight")));
		dogBean.setContent(multi.getParameter("content"));
		
		// ------------------ DB에 둘 다 저장!!!! ------------------
		// 사용자가 올리는 원본 파일명 => 사용자에게 다운로드할 파일 이름으로 적용
		String OriginalFilename = multi.getOriginalFileName((String)multi.getFileNames().nextElement());
		// 실제 서버에 저장되는 파일 이름 => 실제 다운로드할 파일명에 사용
		String realFileSystemName = multi.getFilesystemName((String)multi.getFileNames().nextElement());

		dogBean.setImage(realFileSystemName);
//		System.out.println("OriginalFilename : " + OriginalFilename);
//		System.out.println("realFileSystemName : " + realFileSystemName);
		
		// 강아지 등록하는 DogRegistService() 클래스
		DogRegistService dogRegistService = new DogRegistService();
		boolean isregistDogSuccess = dogRegistService.registDog(dogBean);
		
		if(isregistDogSuccess) {
			// 강아지 등록 성공시
			action = new ActionForward();
			action.setPath("DogList.dog");
			action.setRedirect(true);
		} else {
			// 실패시
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");		
			out.println("alert('게시물 등록 실패')");
			// 이전 페이지로 돌아가기
			out.println("history.back()");		// 또는 out.println("history.go(-1)"); 
			out.println("</script>");
		}
		
		
		return action;
	}

}
