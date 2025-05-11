---
title: 查看线程
slug: check-threads
abstract: 如何查看一个应用程序的线程? 可以使用哪些工具获得线程? 线程有哪些属性? 这些值分别代表什么意思?
---

A chapter can be marked as a "draft" by renaming the file and adding `.draft` to the numeric prefix, like this:

    010.draft-introduction.md

Draft chapters are not listed in the table of contents,
and they are not included in the chapter numberings.
(To see in your dev environment how all the drafts _would_ be numbered if they weren't drafts,
set `show_drafts_in_dev: true` in [_config.yml](https://github.com/jasongrimes/jekyll-chapterbook/blob/master/_config.yml).)

But drafts _are_ listed in the book [outline](/outline.html).
This enables a workflow in which you start with an outline of your book,
made with empty draft chapters having just a `title` and maybe an `abstract`,
ordered and grouped into parts as needed (and frequently reorganized).

Then you can flesh out the chapters over time,
and when ready,
remove the `.draft` from the file name so it appears in the book.  


---
```
This file is located at: {{ page.path }}
```
---



