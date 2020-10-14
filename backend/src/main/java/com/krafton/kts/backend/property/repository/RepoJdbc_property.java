package com.krafton.kts.backend.property.repository;

import com.krafton.kts.backend.common.JdbcCommon;

import javax.sql.DataSource;

public class RepoJdbc_property extends JdbcCommon implements Repo_property {

    public RepoJdbc_property(DataSource dataSource) {
        super(dataSource);
    }
}
