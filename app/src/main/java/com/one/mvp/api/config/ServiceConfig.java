package com.one.mvp.api.config;

/**
 * Created by swplzj on 17/5/3.
 */

public class ServiceConfig {

  private int dataSourceType;

  private ServiceConfig(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  private void initialize(final Builder builder) {
    this.dataSourceType = builder.dataSourceType;
  }

  public int getDataSourceType() {
    return dataSourceType;
  }

  public static final class Builder {

    private int dataSourceType;

    private Builder() {
    }

    public ServiceConfig build() {
      if (dataSourceType != ConstantApi.APISERVICE_CONFIG) {
        throw new IllegalStateException("The dataSourceType does not support!");
      }
      return new ServiceConfig(this);
    }

    public Builder dataSourceType(int dataSourceType) {
      this.dataSourceType = dataSourceType;
      return this;
    }
  }
}
