package javalab.umc7th_mission.apiPayload.exception.handler;

import javalab.umc7th_mission.apiPayload.code.BaseErrorCode;
import javalab.umc7th_mission.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
