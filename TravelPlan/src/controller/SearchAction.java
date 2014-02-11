package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import formbeans.SearchForm;

public class SearchAction extends Action {
	private FormBeanFactory<SearchForm> formBeanFactory = FormBeanFactory
			.getInstance(SearchForm.class);


	public SearchAction(Model model) {
	}

	public String getName() {
		return "search.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			SearchForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {
				return "search.jsp";
			}

			Transaction.begin();
			
			//any errors
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				Transaction.commit();
				return "search.jsp";
			}

			Transaction.commit();

			return "search.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "search.jsp";
		} catch (RollbackException e) {
			return "search.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "search.jsp";
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
