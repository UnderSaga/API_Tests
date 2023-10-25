package models.ResponseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersListResponse {
    public Integer page;
    public Integer per_page;
    public Integer total;
    public Integer total_pages;
    public List<DataResponse> data;
    public SupportResponse support;
}
