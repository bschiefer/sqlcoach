/*******************************************************************************
 * This file is part of SQLCoach.
 *
 * SQLCoach is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SQLCoach is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
Content.languages = { 
	css        : { name : 'CSS',        extensions : 'css'			  },
	generic    : { name : 'Generic',    extensions : '' 			  },
	html       : { name : 'HTML',       extensions : 'html,htm,xhtml' },
	java       : { name : 'Java',       extensions : 'java'           },
	javascript : { name : 'JavaScript', extensions : 'js'             },	
	perl       : { name : 'Perl',       extensions : 'pl,cgi'         },
	php        : { name : 'PHP',        extensions : 'php,phtml'      },
	text       : { name : 'Plain Text', extensions : 'txt'   	      },
	sql        : { name : 'SQL',    	extensions : 'sql' 			  }
}

Content.messages =  {
	browserError  : 'Your browser is not supported.\nSyntax highlighting is turned off\nand some features are disabled.',
	fileSaveError : 'There is a problem saving your file.\nPlease try again.'
}

Content.menu = {
	untitledFile : 'Untitle source code',
	options      : 'Options',
	languages    : 'Languages',
	autoComplete : 'Auto complete',
	autoIndent   : 'Auto indent',		
	fullScreen   : 'Full screen',
	lineNumbers  : 'Line numbers',
	save         : 'Save',
	saved        : 'Salvo',
	about        : 'Sobre',
	snippets     : 'Snippets',
	help         : 'Help',
	website      : 'Website'
}
