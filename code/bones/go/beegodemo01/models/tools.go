package models

import "time"

func Unix2Date(timestamp int64)string  {
	t :=time.Unix(int64(timestamp),0)
	return t.Format("2006-01-02 15:04:05")
}
