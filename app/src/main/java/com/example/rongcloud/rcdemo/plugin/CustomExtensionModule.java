package com.example.rongcloud.rcdemo.plugin;

import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.model.Conversation;

/**
 * Created by rongcloud on 2017/7/25.
 */

public class CustomExtensionModule extends DefaultExtensionModule {
    private CustomPlugin customPlugin = new CustomPlugin();
    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
        List<IPluginModule> pluginModules = super.getPluginModules(conversationType);
        pluginModules.add(customPlugin);
        return pluginModules;
    }
}
