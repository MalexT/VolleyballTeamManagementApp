# Volleyball team management

Volleyball team management web application for faculty purposes

## Requirements
MySql
Leiningen

## Usage

Change username and password in dbconfig file to match those on your computer (or create a new user with these credentials).
You might need to change the port on which the app is running (you can find port number in project.clj file)
and then run:

    $ lein ring server

If you kill the app and then want to run it again with command mentioned above
You should drop database 'volleyballteammanagement' and then run:

    $ lein ring server

## Examples

User can perform CRUD operations on 
 - Team, 
 - Player

## License

Copyright © 2021 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
