/*
 * This file is generated by jOOQ.
*/
package com.stubbornjava.cms.server.generated;


import com.stubbornjava.cms.server.generated.tables.AppTable;
import com.stubbornjava.cms.server.generated.tables.PostTable;
import com.stubbornjava.cms.server.generated.tables.PostTagLinksTable;
import com.stubbornjava.cms.server.generated.tables.PostTagTable;
import com.stubbornjava.cms.server.generated.tables.UserTable;
import com.stubbornjava.cms.server.generated.tables._FlywayTable;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in sj_cms
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>sj_cms.app</code>.
     */
    public static final AppTable APP = com.stubbornjava.cms.server.generated.tables.AppTable.APP;

    /**
     * The table <code>sj_cms.post</code>.
     */
    public static final PostTable POST = com.stubbornjava.cms.server.generated.tables.PostTable.POST;

    /**
     * The table <code>sj_cms.post_tag</code>.
     */
    public static final PostTagTable POST_TAG = com.stubbornjava.cms.server.generated.tables.PostTagTable.POST_TAG;

    /**
     * The table <code>sj_cms.post_tag_links</code>.
     */
    public static final PostTagLinksTable POST_TAG_LINKS = com.stubbornjava.cms.server.generated.tables.PostTagLinksTable.POST_TAG_LINKS;

    /**
     * The table <code>sj_cms.user</code>.
     */
    public static final UserTable USER = com.stubbornjava.cms.server.generated.tables.UserTable.USER;

    /**
     * The table <code>sj_cms._flyway</code>.
     */
    public static final _FlywayTable _FLYWAY = com.stubbornjava.cms.server.generated.tables._FlywayTable._FLYWAY;
}
