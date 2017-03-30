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
/*
 * CodePress regular expressions for SQL syntax highlighting
 * By Merlin Moncure
 * ergaenzt (bs)
 */
 
// SQL
Language.syntax = [
	{ input : /\'(.*?)(\')/g, output : '<s>\'$1$2</s>' }, // strings single quote
	{ input : /\b(add|after|aggregate|alias|all|and|as|authorization|between|by|cascade|cache|cache|called|case|check|column|comment|constraint|createdb|createuser|cycle|database|default|deferrable|deferred|diagnostics|distinct|domain|each|else|elseif|elsif|encrypted|except|exception|for|foreign|from|from|full|function|get|group|having|if|immediate|immutable|in|increment|initially|increment|index|inherits|inner|input|intersect|into|invoker|is|join|key|language|left|limit|local|loop|match|maxvalue|minvalue|natural|nextval|no|nocreatedb|nocreateuser|not|null|of|offset|oids|on|only|operator|or|order|outer|owner|partial|password|perform|plpgsql|primary|record|references|replace|restrict|return|returns|right|row|rule|schema|security|sequence|session|sql|stable|statistics|table|temp|temporary|then|time|to|transaction|trigger|type|unencrypted|union|unique|user|using|valid|value|values|view|volatile|when|where|with|without|zone)\b/gi, output : '<b>$1</b>' }, // reserved words
	{ input : /\b(asc|avg|count|desc|exists|like|max|min|minus|length|coalesce)\b/gi, output : '<b>$1</b>' }, // additional key words (bs)
	{ input : /\b(bigint|bigserial|bit|boolean|box|bytea|char|character|cidr|circle|date|decimal|double|float4|float8|inet|int2|int4|int8|integer|interval|line|lseg|macaddr|money|numeric|oid|path|point|polygon|precision|real|refcursor|serial|serial4|serial8|smallint|text|timestamp|varbit|varchar)\b/gi, output : '<u>$1</u>' }, // types
	{ input : /\b(abort|alter|analyze|begin|checkpoint|close|cluster|comment|commit|copy|create|deallocate|declare|delete|drop|end|execute|explain|fetch|grant|insert|listen|load|lock|move|notify|prepare|reindex|reset|restart|revoke|rollback|select|set|show|start|truncate|unlisten|update)\b/gi, output : '<a>$1</a>' }, // commands
	{ input : /([^:]|^)\-\-(.*?)(<br|<\/P)/g, output: '$1<i>--$2</i>$3' } // comments //	
];

Language.snippets = [
	{ input : 'select', output : 'select $0 from  where ' }
];

Language.complete = [
	{ input : '\'', output : '\'$0\'' },
	{ input : '"', output : '"$0"' },
	{ input : '(', output : '\($0\)' },
	{ input : '[', output : '\[$0\]' },
	{ input : '{', output : '{\n\t$0\n}' }		
];

Language.shortcuts = [];



