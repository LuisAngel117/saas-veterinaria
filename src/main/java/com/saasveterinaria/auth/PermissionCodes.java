package com.saasveterinaria.auth;

import java.util.List;

public final class PermissionCodes {
  public static final String AUTH_LOGIN = "AUTH_LOGIN";
  public static final String AUTH_REFRESH = "AUTH_REFRESH";
  public static final String AUTH_LOGOUT = "AUTH_LOGOUT";
  public static final String AUTH_2FA_ENROLL = "AUTH_2FA_ENROLL";
  public static final String AUTH_2FA_RESET = "AUTH_2FA_RESET";

  public static final String BRANCH_SELECT = "BRANCH_SELECT";
  public static final String BRANCH_VIEW = "BRANCH_VIEW";

  public static final String AGENDA_VIEW = "AGENDA_VIEW";
  public static final String AGENDA_CREATE = "AGENDA_CREATE";
  public static final String AGENDA_EDIT = "AGENDA_EDIT";
  public static final String AGENDA_CONFIRM = "AGENDA_CONFIRM";
  public static final String AGENDA_CANCEL = "AGENDA_CANCEL";
  public static final String AGENDA_CHECKIN = "AGENDA_CHECKIN";
  public static final String AGENDA_START_SERVICE = "AGENDA_START_SERVICE";
  public static final String AGENDA_CLOSE = "AGENDA_CLOSE";
  public static final String AGENDA_OVERRIDE_OVERLAP = "AGENDA_OVERRIDE_OVERLAP";

  public static final String CLIENT_VIEW = "CLIENT_VIEW";
  public static final String CLIENT_CREATE = "CLIENT_CREATE";
  public static final String CLIENT_EDIT = "CLIENT_EDIT";
  public static final String PET_VIEW = "PET_VIEW";
  public static final String PET_CREATE = "PET_CREATE";
  public static final String PET_EDIT = "PET_EDIT";

  public static final String ENCOUNTER_VIEW = "ENCOUNTER_VIEW";
  public static final String ENCOUNTER_CREATE = "ENCOUNTER_CREATE";
  public static final String ENCOUNTER_EDIT = "ENCOUNTER_EDIT";
  public static final String ENCOUNTER_CLOSE = "ENCOUNTER_CLOSE";
  public static final String ENCOUNTER_REOPEN = "ENCOUNTER_REOPEN";
  public static final String ENCOUNTER_EDIT_CLOSED = "ENCOUNTER_EDIT_CLOSED";

  public static final String ATTACHMENT_UPLOAD = "ATTACHMENT_UPLOAD";
  public static final String ATTACHMENT_VIEW = "ATTACHMENT_VIEW";

  public static final String SERVICE_VIEW = "SERVICE_VIEW";
  public static final String SERVICE_CREATE = "SERVICE_CREATE";
  public static final String SERVICE_EDIT = "SERVICE_EDIT";
  public static final String SERVICE_BOM_EDIT = "SERVICE_BOM_EDIT";

  public static final String INVENTORY_VIEW = "INVENTORY_VIEW";
  public static final String INVENTORY_PRODUCT_CREATE = "INVENTORY_PRODUCT_CREATE";
  public static final String INVENTORY_PRODUCT_EDIT = "INVENTORY_PRODUCT_EDIT";
  public static final String INVENTORY_MOVEMENT_IN = "INVENTORY_MOVEMENT_IN";
  public static final String INVENTORY_MOVEMENT_OUT = "INVENTORY_MOVEMENT_OUT";
  public static final String INVENTORY_ADJUST = "INVENTORY_ADJUST";
  public static final String INVENTORY_OVERRIDE_NEGATIVE = "INVENTORY_OVERRIDE_NEGATIVE";

  public static final String INVOICE_VIEW = "INVOICE_VIEW";
  public static final String INVOICE_CREATE = "INVOICE_CREATE";
  public static final String INVOICE_EDIT = "INVOICE_EDIT";
  public static final String INVOICE_PRICE_OVERRIDE = "INVOICE_PRICE_OVERRIDE";
  public static final String PAYMENT_CREATE = "PAYMENT_CREATE";
  public static final String INVOICE_ANNUL = "INVOICE_ANNUL";

  public static final String REPORT_VIEW = "REPORT_VIEW";
  public static final String REPORT_EXPORT = "REPORT_EXPORT";

  public static final String USER_VIEW = "USER_VIEW";
  public static final String USER_CREATE = "USER_CREATE";
  public static final String USER_EDIT = "USER_EDIT";
  public static final String ROLE_ASSIGN = "ROLE_ASSIGN";
  public static final String SETTINGS_TAX_UPDATE = "SETTINGS_TAX_UPDATE";
  public static final String FEATURE_FLAG_UPDATE = "FEATURE_FLAG_UPDATE";
  public static final String AUDIT_VIEW = "AUDIT_VIEW";

  public static final List<String> ALL = List.of(
      AUTH_LOGIN,
      AUTH_REFRESH,
      AUTH_LOGOUT,
      AUTH_2FA_ENROLL,
      AUTH_2FA_RESET,
      BRANCH_SELECT,
      BRANCH_VIEW,
      AGENDA_VIEW,
      AGENDA_CREATE,
      AGENDA_EDIT,
      AGENDA_CONFIRM,
      AGENDA_CANCEL,
      AGENDA_CHECKIN,
      AGENDA_START_SERVICE,
      AGENDA_CLOSE,
      AGENDA_OVERRIDE_OVERLAP,
      CLIENT_VIEW,
      CLIENT_CREATE,
      CLIENT_EDIT,
      PET_VIEW,
      PET_CREATE,
      PET_EDIT,
      ENCOUNTER_VIEW,
      ENCOUNTER_CREATE,
      ENCOUNTER_EDIT,
      ENCOUNTER_CLOSE,
      ENCOUNTER_REOPEN,
      ENCOUNTER_EDIT_CLOSED,
      ATTACHMENT_UPLOAD,
      ATTACHMENT_VIEW,
      SERVICE_VIEW,
      SERVICE_CREATE,
      SERVICE_EDIT,
      SERVICE_BOM_EDIT,
      INVENTORY_VIEW,
      INVENTORY_PRODUCT_CREATE,
      INVENTORY_PRODUCT_EDIT,
      INVENTORY_MOVEMENT_IN,
      INVENTORY_MOVEMENT_OUT,
      INVENTORY_ADJUST,
      INVENTORY_OVERRIDE_NEGATIVE,
      INVOICE_VIEW,
      INVOICE_CREATE,
      INVOICE_EDIT,
      INVOICE_PRICE_OVERRIDE,
      PAYMENT_CREATE,
      INVOICE_ANNUL,
      REPORT_VIEW,
      REPORT_EXPORT,
      USER_VIEW,
      USER_CREATE,
      USER_EDIT,
      ROLE_ASSIGN,
      SETTINGS_TAX_UPDATE,
      FEATURE_FLAG_UPDATE,
      AUDIT_VIEW
  );

  private PermissionCodes() {}
}
