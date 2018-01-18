@echo on
@echo =============================================================
@echo $                                                           $
@echo $                     Nepxion Permission                    $
@echo $                                                           $
@echo $                                                           $
@echo $                                                           $
@echo $  Nepxion Studio All Right Reserved                        $
@echo $  Copyright (C) 2017-2050                                  $
@echo $                                                           $
@echo =============================================================
@echo.
@echo off

@title Nepxion Permission
@color 0a

call mvn clean deploy -DskipTests -e -P release -pl permission-aop -am

pause