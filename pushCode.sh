#!/bin/bash
git add .
read task
git commit -m "$task"
git push origin nak

