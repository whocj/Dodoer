/*===========================================================================
 * Licensed Materials - Property of IBM
 * "Restricted Materials of IBM"
 * 
 * IBM SDK, Java(tm) Technology Edition, v6
 * (C) Copyright IBM Corp. 2006, 2006. All
 *Rights Reserved
 *
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 *===========================================================================
 */
/*
 * @(#)RejectedExecutionHandler.java	1.5 06/07/13
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.summer.whm.spider.distributed.utils.jdk7;

/**
 * A handler for tasks that cannot be executed by a {@link ThreadPoolExecutor}.
 *
 * @since 1.5
 * @author Doug Lea
 */
public interface RejectedExecutionHandler {

    void rejectedExecution(Runnable r, ThreadPoolExecutor executor);
}
