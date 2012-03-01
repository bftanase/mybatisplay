package models.mybatis.mappers;

import java.util.List;

import models.domain.GuestBook;

public interface GuestBookMapper {
  public List<GuestBook> selectAll();
}
