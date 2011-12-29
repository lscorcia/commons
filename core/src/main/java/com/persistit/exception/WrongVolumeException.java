/**
 * Copyright (C) 2011 Akiban Technologies Inc.
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses.
 */

package com.persistit.exception;


/**
 * Thrown by {@link com.persistit.Volume} if the volume file has a different
 * internal ID value than expected. This condition can signify that a volume
 * file has been renamed or the wrong file has been restored to a configuration.
 * 
 */
public class WrongVolumeException extends PersistitException {
    private static final long serialVersionUID = 9119544306031815864L;

    public WrongVolumeException() {
        super();
    }

    public WrongVolumeException(String msg) {
        super(msg);
    }

}
