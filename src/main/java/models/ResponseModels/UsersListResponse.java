package models.ResponseModels;

import java.util.List;

public class UsersListResponse {
    public Integer page;
    public Integer per_page;
    public Integer total;
    public Integer total_pages;
    public List<DataResponse> data;
    public SupportResponse support;
}
