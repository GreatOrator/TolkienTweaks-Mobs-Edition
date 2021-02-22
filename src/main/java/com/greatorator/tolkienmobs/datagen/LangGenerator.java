package com.greatorator.tolkienmobs.datagen;

import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.data.LanguageProvider;

/**
 * Created by brandon3055 on 21/5/20.
 */
//@formatter:off
public class LangGenerator extends LanguageProvider {
    public LangGenerator(DataGenerator gen) {
        super(gen, TolkienMobs.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        blocks();
        items();
        quest();
        food();
        potion();
        coinToken();
        enchants();
        records();
        tools();
        entities();
        creativeTabGroups();
    }

    private void blocks(PrefixHelper helper) {
        helper.add(TTMContent.BLOCK_MITHRIL.get(),                               "Mithril Block");
    }

    private void items() {
        //Components
        helper.add(TTMContent.INGOT_MITHRIL.get(),                                 "Mithril Ingot");
    }

    //Then i just left in most of my translations so you can see how i handle them

    private void itemProps(PrefixHelper helper) {
        helper.setPrefix("item_prop.draconicevolution");
        helper.add("attack_aoe",                                                "Attack Radius");
        helper.add("attack_aoe.info",                                           "Allows you to adjust the area covered by this weapon's swing.\nThis covers a 100 degree arc in the direction you are looking.");
        helper.add("mining_aoe",                                                "Mining AOE");
        helper.add("mining_aoe.info",                                           "Allows you to adjust this tools mining area of effect.");
        helper.add("aoe_safe",                                                  "AOE Safe Mode");
        helper.add("aoe_safe.info",                                             "When enabled, this tool will not break anything if it detects a tile entity within its AOE range. This can help prevent you from accidentally breaking half your base with a single misclick.");
        helper.add("aoe_safe.blocked",                                          "§9(§aAOE §aSafe §aMode §ais §aenabled§9) §cOperation §cCanceled §cbecause §ca §ctile §centity §cwas §cdetected");

        helper.add("mining_speed",                                              "Dig Speed Multiplier");
        helper.add("mining_speed.info",                                         "Allows you to adjust how fast this tool breaks blocks. Useful if you need precision over speed.");
        helper.add("walk_speed",                                                "Walk Speed");
        helper.add("walk_speed.info",                                           "Allows you to adjust the speed boost that is applied while you are walking.");
        helper.add("run_speed",                                                 "Sprint Speed");
        helper.add("run_speed.info",                                            "Allows you to adjust the speed boost that is applied while you are sprinting.");
        helper.add("jump_boost",                                                "Jump Boost");
        helper.add("jump_boost.info",                                           "Adjust jump boost.");
        helper.add("jump_boost_run",                                            "Run Jump Boost");
        helper.add("jump_boost_run.info",                                       "Adjust running jump boost.");

        helper.add("shield_mod.enabled",                                        "Shield Toggle");
        helper.add("shield_mod.enabled.info",                                   "Allows you to disable your shield. This prevents your shield from using power but leaves you vulnerable to damage.");
        helper.add("shield_mod.always_visible",                                 "Shield Always Visible");
        helper.add("shield_mod.always_visible.info",                            "Purely cosmetic. If false your shield will only be visible when it absorbs damage.");

        helper.add("feed_mod.consume_food",                                     "Consume Food.");
        helper.add("feed_mod.consume_food.info",                                "When enabled this module will automatically consume food from your inventory to fill its internal storage.");

        helper.add("flight_mod.elytra",                                         "Elytra Flight");
        helper.add("flight_mod.elytra.info",                                    "Enables you to enable / disable elytra flight.");
        helper.add("flight_mod.creative",                                       "Creative Flight");
        helper.add("flight_mod.creative.info",                                  "Enables you to enable / disable creative flight.");
        helper.add("flight_mod.elytra_boost",                                   "Elytra Boost");
        helper.add("flight_mod.elytra_boost.info",                              "Allows you to adjust the power of Elytra speed boost.\nSpeed boost can be activated by pressing Forward + Sprint while flying.");

        helper.add("charge_held_item",                                          "Charge Held Item");
        //helper.add("charge_held_item.info",                                     "");
        helper.add("charge_armor",                                              "Charge Armor");
        //helper.add("charge_armor.info",                                         "");
        helper.add("charge_hot_bar",                                            "Charge Hotbar");
        //helper.add("charge_hot_bar.info",                                       "");
        helper.add("charge_main",                                               "Charge Main Inventory");
        //helper.add("charge_main.info",                                          "");
        helper.add("charge_baubles",                                            "Charge Baubles");
        //helper.add("charge_baubles.info",                                       "");
        helper.add("charge_curios",                                             "Charge Curios");
        helper.add("charge_curios.info",                                        "Charge items in curios equipment slots.");
    }

    private void guis(PrefixHelper helper) {
        //Item Config
        helper.setPrefix("gui.draconicevolution.item_config");
        helper.add("name",                                                      "Configure Equipment");
        helper.add("configure",                                                 "Configure");
        helper.add("toggle_hidden.info",                                        "Show / Hide Inventory");
        helper.add("toggle_advanced.info",                                      "Toggle advanced config mode");
        helper.add("delete_zone.info",                                          "Drop a property or group here to delete it.");
        helper.add("add_group",                                                 "Add Group");
        helper.add("add_group.info",                                            "Add a New Property Group");
        helper.add("click_and_drag_to_place",                                   "Click and Drag");
        helper.add("edit_preset.info",                                          "Edit Preset Properties");
        helper.add("expand_group.info",                                         "Expand Group");
        helper.add("collapse_group.info",                                       "Collapse Group");
        helper.add("move_group.info",                                           "Click and drag to move this group.");
        helper.add("delete_group.info",                                         "Delete");
        helper.add("copy_group.info",                                           "Copy Group");
        helper.add("toggle_preset.info",                                        "Toggle preset mode\nConverts this property group into a property preset.");
        helper.add("apply_preset",                                              "Apply Preset");
        helper.add("move_prop.info",                                            "Click and drag to move property.");
        helper.add("drop_create_group.info",                                    "Drop to Create Group");
        helper.add("add_to_group.info",                                         "Add to Group");
        helper.add("drop_to_delete.info",                                       "Drop to delete.");
        helper.add("drop_prop_here",                                            "Drop Property Here");
        helper.add("global.info",                                               "Toggle global mode\nWhen in global mode, a property will be applied to all equipment of the same type.\nPlease note that when in global mode, the displayed value may not match the items actual value.");
        helper.add("provider_unavailable",                                      "Item not available.");
        helper.add("select_item_to_get_started",                                "Select a configurable item to get started.");
        helper.add("options",                                                   "Options");
        helper.add("hide_unavailable",                                          "Hide Unavailable");
        helper.add("hide_unavailable.info",                                     "Hide properties if the associated item is not available.");
        helper.add("show_unavailable",                                          "Show Unavailable");
        helper.add("show_unavailable.info",                                     "Show properties if the associated item is not available.");
        helper.add("disable_snapping",                                          "Disable Snapping");
        helper.add("disable_snapping.info",                                     "Toggle Property/Group Position Snapping");
        helper.add("enable_snapping",                                           "Enable Snapping");
        helper.add("disable_visualization",                                     "Disable Visualization");
        helper.add("disable_visualization.info",                                "Toggle the highlight/animation that occurs over a properties associated item when hovering over or editing a property.");
        helper.add("enable_visualization",                                      "Enable Visualization");
        helper.add("hide_group_button",                                         "Hide \"New Group\" Button");
        helper.add("show_group_button",                                         "Show \"New Group\" Button");
        helper.add("hide_delete_zone",                                          "Hide \"Delete Zone\"");
        helper.add("show_delete_zone",                                          "Show \"Delete Zone\"");
        helper.add("disable_adv_xover",                                         "Disable Advanced Crossover");
        helper.add("disable_adv_xover.info",                                    "Hide configured properties, property groups and presets when in simple configuration mode.");
        helper.add("enable_adv_xover",                                          "Enable Advanced Crossover");
        helper.add("enable_adv_xover.info",                                     "Show configured properties, property groups and presets when in simple configuration mode.");
        helper.add("not_bound",                                                 "Not Bound");
        helper.add("toggle_global_binding.info",                                "Global Binding\nWill allow you to use this keybind when this GUI is closed.\nThis will work with any key regardless of weather or not it conflicts with another keybinding.");
        helper.add("set_key_bind.info",                                         "Click to set a keybind for this preset.");
        helper.add("open_modules.info",                                         "Open Module Configuration GUI");

        helper.setPrefix("gui.draconicevolution.boolean_property");
        helper.add("true",                                                      "True");
        helper.add("false",                                                     "False");
        helper.add("enabled",                                                   "Enabled");
        helper.add("disabled",                                                  "Disabled");
        helper.add("active",                                                    "Active");
        helper.add("inactive",                                                  "Inactive");
        helper.add("yes",                                                       "Yes");
        helper.add("no",                                                        "No");

        //Module GUI
        helper.setPrefix("gui.draconicevolution.modular_item");
        helper.add("name",                                                      "Configure Modules");
        helper.add("modules",                                                   "Modules");
        helper.add("open_item_config.info",                                     "Open Item Configuration GUI");
        helper.add("no_module_hosts",                                           "You do not have any modular items in your inventory!");
        helper.add("module_grid",                                               "Module Grid");

        //Generator
        helper.setPrefix("gui.draconicevolution.generator");
        helper.add("fuel_efficiency",                                           "Fuel Efficiency:");
        helper.add("output_power",                                              "Output Power:");
        helper.add("current_fuel_value",                                        "Current Fuel Value:");
        helper.add("mode_eco_plus",                                             "Eco Plus");
        helper.add("mode_eco_plus.info",                                        "Eco Plus\nSignificantly increased fuel efficiency.\nSignificantly decreased output power.");
        helper.add("mode_eco",                                                  "Eco");
        helper.add("mode_eco.info",                                             "Eco Mode\nIncreased fuel efficiency.\nDecreased output power.");
        helper.add("mode_normal",                                               "Normal");
        helper.add("mode_normal.info",                                          "Normal Mode\nStandard output power and efficiency.\nSimilar to other basic generators.");
        helper.add("mode_performance",                                          "Performance");
        helper.add("mode_performance.info",                                     "Performance Mode\nProvides a worthwhile increase in output power\nfor a small fuel efficiency penalty.");
        helper.add("mode_performance_plus",                                     "Overdrive");
        helper.add("mode_performance_plus.info",                                "Overdrive Mode\nNeed all the power you can get?\nHave plenty of fuel to burn?\nThis is the mode for you!");

        //Grinder
        helper.setPrefix("gui.draconicevolution.grinder");
        helper.add("aoe",                                                       "AOE:");
        helper.add("aoe.info",                                                  "Increment's the grinder's Area Of Effect.\n(The area in which it will kill mobs)");
        helper.add("show_aoe",                                                  "Show AOE");
        helper.add("collect.items",                                             "Collect Items");
        helper.add("collect.items.info",                                        "If enabled will collect items within the kill area and insert them into an adjacent inventory.");
        helper.add("collect.xp",                                                "Collect XP");
        helper.add("collect.xp.info",                                           "If enabled will collect experience dropped within the kill area and store itinternally.\nThis XP can then be claimed by a player or piped out if there is a mod installed that adds liquid XP.");
        helper.add("claim.xp",                                                  "Claim XP");
        helper.add("claim.xp.info",                                             "Claim all stored XP");
        helper.add("claim.xp.level.info",                                       "Claim 1 experience level");
        helper.add("claim.xp.levels.info",                                      "Claim %s experience levels");
        helper.add("stored_xp",                                                 "Stored Experience:");
        helper.add("stored_xp.raw",                                             "(Raw XP)");
        helper.add("weapon_slot",                                               "Optional weapon to use when attacking.");

        //Reactor
        helper.setPrefix("gui.draconicevolution.reactor");
        helper.add("title",                                                     "Draconic Reactor");
        helper.add("core_volume",                                               "Core Volume");
        helper.add("core_volume.info",                                          "This shows the total volume of matter within the reactor in cubic meters (Draconium + Chaos). This value will only change when you add or remove fuel.");
        helper.add("gen_rate",                                                  "Generation Rate");
        helper.add("gen_rate.info",                                             "This is the current RF/t being generated by the reactor.");
        helper.add("field_rate",                                                "Field Input Rate");
        helper.add("field_rate.info",                                           "This is the exact RF/t input required to maintain the current field strength. As field strength increases, this will increase exponentially.");
        helper.add("convert_rate",                                              "Fuel Conversion Rate");
        helper.add("convert_rate.info",                                         "This is how fast the reactor is currently using fuel. As the reactor saturation increases, this will go down.");
        helper.add("go_boom_now",                                               "Emergency shield reserve is now active but it wont last long! There is no way to stop the overload; the stabilizers are fried. I suggest you run!");
        helper.add("fuel_in",                                                   "Fuel (in)");
        helper.add("chaos_out",                                                 "Chaos (out)");
        helper.add("status",                                                    "Status");

        helper.add("charge",                                                    "Charge");
        helper.add("activate",                                                  "Activate");
        helper.add("shutdown",                                                  "Shutdown");
        helper.add("rs_mode",                                                   "Redstone\nMode");
        helper.add("rs_mode.info",                                              "Configure the comparator output for this reactor component.");
        helper.add("sas",                                                       "SAS");
        helper.add("sas.info",                                                  "Semi-Automated Shutdown. When enabled the reactor will automatically initiate shutdown when the Temperature drops bellow 2500C and Saturation reaches 99%% This can be used to automatically shutdown your reactor in the event of a malfunction or just when it needs to be refueled.");

        helper.add("rs_mode_temp",                                              "Temp");
        helper.add("rs_mode_temp.info",                                         "Will output a signal from 0 to 15 as temperature rises up to 10000.");
        helper.add("rs_mode_temp_inv",                                          "-Temp");
        helper.add("rs_mode_temp_inv.info",                                     "This is the same as Temperature except the signal is inverted.");
        helper.add("rs_mode_field",                                             "Shield");
        helper.add("rs_mode_field.info",                                        "Will output a signal from 0 to 15 as the shield strength fluctuates between 0 and 100%, Signal of 1 = > 10% shield and Signal of 15 = >= 90% shield power.");
        helper.add("rs_mode_field_inv",                                         "-Shield");
        helper.add("rs_mode_field_inv.info",                                    "-This is the same as Shield except the signal is inverted.");
        helper.add("rs_mode_sat",                                               "Saturation");
        helper.add("rs_mode_sat.info",                                          "Will output a signal from 0 to 15 as the saturation level fluctuates between 0 and 100%.");
        helper.add("rs_mode_sat_inv",                                           "-Saturation");
        helper.add("rs_mode_sat_inv.info",                                      "This is the same as Saturation except the signal is inverted.");
        helper.add("rs_mode_fuel",                                              "Conversion");
        helper.add("rs_mode_fuel.info",                                         "Will output a signal from 0 to 15 as the fuel conversion level increases from 0 to 100%, Signal of 15 = >= 90% Conversion.");
        helper.add("rs_mode_fuel_inv",                                          "-Conversion");
        helper.add("rs_mode_fuel_inv.info",                                     "This is the same as Conversion except the signal is inverted.");

        helper.add("reaction_temp",                                             "Core Temperature");
        helper.add("field_strength",                                            "Containment Field Strength");
        helper.add("energy_saturation",                                         "Energy Saturation");
        helper.add("fuel_conversion",                                           "Fuel Conversion Level");

        helper.setPrefix("gui.reactor.status");
        helper.add("invalid.info",                                              "Invalid Setup");
        helper.add("cold.info",                                                 "Offline");
        helper.add("warming_up.info",                                           "Warming Up");
        helper.add("running.info",                                              "Online");
        helper.add("stopping.info",                                             "Stopping");
        helper.add("cooling.info",                                              "Cooling Down");
        helper.add("beyond_hope.info",                                          "Explosion Imminent!!!");

        //Flow Gate
        helper.setPrefix("gui.draconicevolution.flow_gate");
        helper.add("overridden",                                                "Overridden");
        helper.add("overridden.info",                                           "Default controls have been disabled by a computer.");
        helper.add("redstone_high",                                             "Redstone Signal High");
        helper.add("redstone_high.info",                                        "The flow that will be allowed when to pass when receiving a redstone signal of 15.");
        helper.add("apply",                                                     "Apply");
        helper.add("redstone_low",                                              "Redstone Signal Low");
        helper.add("redstone_low.info",                                         "The flow that will be allowed when to pass when receiving a redstone signal of 0.");
        helper.add("flow",                                                      "Flow");
        helper.add("flow.info",                                                 "The actual flow will vary between the two given values depending on the strength of the redstone signal being supplied to the block.");

        //Energy Transfuser
        helper.setPrefix("gui.draconicevolution.transfuser");
        helper.add("mode_charge",                                               "Charge\n - Accepts power from external sources\n - Accepts power from buffer slots\n - Can be extracted when fully charged");
        helper.add("mode_discharge",                                            "Discharge\n - Discharges to external consumers\n - Discharges to buffer slots\n - Can be extracted when fully discharged");
        helper.add("mode_buffer",                                               "Buffer\n - Accepts power from external sources\n - Accepts power from discharge slots\n - Discharges to external consumers\n - Discharges to charge slots");
        helper.add("mode_disabled",                                             "Disabled\n - Slot is disabled");
        helper.add("sequential_charge",                                         "Sequential Input Priority\nThe left most item will receive charging priority.\nOnce that item is full the next will receive priority.");
        helper.add("balanced_charge",                                           "Balanced Input Priority\nPower input will be balanced between all 4 slots\nbut each slot will be limited to 1/4 of the total input rate.");

        //Energy Dislocator
        helper.setPrefix("gui.draconicevolution.dislocator");
        helper.add("add",                                                       "Add New");
        helper.add("add.info",                                                  "Add current position\n- insert below selected");
        helper.add("add_top.info",                                              "Add current position\n- add to top of list");
        helper.add("add_bottom.info",                                           "Add current position\n- add to bottom of list");
        helper.add("fuel",                                                      "Fuel:");
        helper.add("fuel.info",                                                 "The total number of teleports remaining.");
        helper.add("update",                                                    "Set Here");
        helper.add("update.info",                                               "Update the selected location to your current coordinates.");
        helper.add("fuel_add_1.info",                                           "Add 1 Ender Pearl from your inventory.\nEnder pearls are used as teleport fuel.");
        helper.add("fuel_add_16.info",                                          "Add 16 Ender Pearls from your inventory.\nEnder pearls are used as teleport fuel.");
        helper.add("fuel_add_all.info",                                         "Add all Ender Pearls from your inventory.\nEnder pearls are used as teleport fuel.");
        helper.add("right_click_tp",                                            "Right click to teleport");
        helper.add("double_click_name",                                         "Double left click to rename.");
        helper.add("must_unlock",                                               "(must be unlocked)");
        helper.add("drag_to_move",                                              "Left click and drag to move.");
        helper.add("delete.info",                                               "Delete");
        helper.add("edit_lock.info",                                            "Toggle edit lock");
        helper.add("right_click_mode.info",                                     "Switch between \"Teleport to Selected\" and \"Blink\" on right click.\\nYou can also configure keybindings.");
        helper.add("mode_blink",                                                "Use Mode:\nBlink");
        helper.add("mode_tp",                                                   "Use Mode:\nTeleport");

    }

    private void hudAndMessages(PrefixHelper helper) {
        //Energy Network
        helper.setPrefix("gui.draconicevolution.energy_net");
        helper.add("pos_saved_to_tool",                                         "Block Position saved to tool. (Sneak + Right click air to clear)");
        helper.add("pos_cleared",                                               "Position Cleared");
        helper.add("tool_not_bound",                                            "Tool not bound! Sneak + Right click to bind.");
        helper.add("bound_to_invalid",                                          "The tool is bound to an invalid block!");
        helper.add("link_broken",                                               "Link Broken!");
        helper.add("devices_linked",                                            "Devices Linked!");
        helper.add("link_limit_reached_this",                                   "This device has reached its connection limit!");
        helper.add("link_limit_reached_target",                                 "The target device has reached its connection limit!");
        helper.add("this_range_limit",                                          "The target device is too far away!");
        helper.add("target_range_limit",                                        "This device is too far from the target device!");
        helper.add("device_invalid",                                            "That is not a valid device!");
        helper.add("link_failed_unknown",                                       "Link Failed! [Reason Unknown...]");
        helper.add("link_to_self",                                              "You can not link a device to itself...");
        helper.add("hud_charge",                                                "Charge");
        helper.add("hud_links",                                                 "Links");
        helper.add("hud_wireless_links",                                        "Wireless Links");
        helper.add("io_output_true",                                            "I/O Mode:%s Output");
        helper.add("io_output_false",                                           "I/O Mode:%s Input");
        helper.add("side_can_not_receive",                                      "That tile can not receive energy on that side!.");
        helper.add("side_can_not_extract",                                      "That tile can not supply energy on that side!.");
        helper.add("max_receivers",                                             "Receiver Limit Reached!");
        helper.add("output",                                                    "Output");
        helper.add("input",                                                     "Input");
        helper.add("identify",                                                  "Identify");
        helper.add("unlink",                                                    "Unlink");
        helper.add("clear_links",                                               "Clear Links");
        helper.add("clear_receivers",                                           "Clear Receivers");

        //Modular Item
        helper.setPrefix("modular_item.draconicevolution");
        helper.add("requires_energy",                                           "This item requires energy modules to function.");
        helper.add("requires_energy_press",                                     "Press %s to open module config.");

        //Dislocator
        helper.setPrefix("dislocate.draconicevolution");
        helper.add("not_set",                                                   "You must first set the destination before you can teleport (Sneak + Right click).");
        helper.add("player_need_advanced",                                      "You need a more powerful dislocator to do that.");
        helper.add("entity_sent_to",                                            "The entity has been sent to");
        helper.add("low_health",                                                "You are too low on health!");
        helper.add("already_bound",                                             "ERROR: This charm can only be bound to one location.");
        helper.add("un_set_info1",                                              "Unbound");
        helper.add("un_set_info2",                                              "Sneak + Right click to bind your current");
        helper.add("un_set_info3",                                              "X, Y, and Z coordinates,");
        helper.add("un_set_info4",                                              "as well as the direction you are facing");
        helper.add("un_set_info5",                                              "and the dimension you are currently in.");
        helper.add("bound_to",                                                  "Bound to");
        helper.add("uses_remain",                                               "%s Uses remaining");

        helper.add("no_fuel",                                                   "OUT OF FUEL!!!");
        helper.add("player_allow",                                              "The player must give their consent by sneaking.");
        helper.add("fuel",                                                      "Fuel:");
        helper.add("teleport_fuel",                                             "Teleport Fuel:");
        helper.add("to_open_gui",                                               "Sneak + Right click to open GUI");
        //helper.add("",                                                          "");
        //helper.add("",                                                          "");


        //Item Dislocator
        helper.setPrefix("item_dislocate.draconicevolution");
        helper.add("activate",                                                  "Item Dislocator Activated");
        helper.add("deactivate",                                                "Item Dislocator Deactivated");
    }

    private void misc(PrefixHelper helper) {
        add("itemGroup.draconicevolution.blocks",                               "Draconic Evolution Blocks");
        add("itemGroup.draconicevolution.items",                                "Draconic Evolution Items");
        add("itemGroup.draconicevolution.modules",                              "Draconic Evolution Modules");
        add("tech_level.draconicevolution.draconium",                           "Draconium");
        add("tech_level.draconicevolution.wyvern",                              "Wyvern");
        add("tech_level.draconicevolution.draconic",                            "Draconic");
        add("tech_level.draconicevolution.chaotic",                             "Chaotic");
        add("entity.draconicevolution.draconic_guardian",                       "Chaos Guardian");
        add("death.attack.draconicevolution.draconic_guardian",                 "%1$s was torn apart by %2$s");
        add("death.attack.draconicevolution.guardian_projectile",               "%1$s was obliterated %2$s");

        //Temporary
        add("gui.de.energyStorageCore.name",                                    "Tier %s Energy Core");
        add("button.de.buildGuide.txt",                                         "Build Guide:");
        add("button.de.assembleCore.txt",                                       "Assemble Core");
        add("button.de.tierUp.txt",                                             "Tier Up");
        add("button.de.tierDown.txt",                                           "Tier Down");
        add("button.de.activate.txt",                                           "Activate");
        add("button.de.deactivate.txt",                                         "Deactivate");
        add("ecore.de.already_assembling.txt",                                  "Assembly already in progress!");
        add("ecore.de.assemble_found_invalid.txt",                              "ERROR: Found invalid block \"%s\" at %s.");
        add("ecore.de.assemble_error_expected_air.txt",                         "ERROR: Expected air block at %s");
        add("ecore.de.assemble_missing_required.txt",                           "ERROR: Unable to complete structure! Missing required block(s) \"%s\".");
        add("gui.de.transfer.txt",                                              "Transfer");
        add("gui.de.capacity.txt",                                              "Capacity");
        add("gui.de.maxStorage.txt",                                            ">> Max Storage <<");
        add("info.bc.charge.txt",                                               "Charge");
        add("gui.de.almostInfinite.txt",                                        "Almost Infinite!");

        add("gui.de.stabilizers.txt",                                           "Stabilizers");
        add("gui.de.invalid.txt",                                               "Invalid");
        add("gui.de.valid.txt",                                                 "Valid");
        add("gui.de.advancedStabilizersRequired.txt",                           "Advanced Stabilizers Required");
        add("gui.de.core.txt",                                                  "Core");
        add("gui.de.inactive.txt",                                              "Inactive");
        add("gui.de.active.txt",                                                "Active");

        add("key.draconicevolution.place_item",                                 "Place Item");
        add("key.draconicevolution.tool_config",                                "Tool Config");
        add("key.draconicevolution.toggle_flight",                              "Toggle Flight");
        add("key.draconicevolution.tool_modules",                               "Tool Modules");
        add("key.draconicevolution.toggle_magnet",                              "Toggle Item Dislocator");
        add("key.draconicevolution.dislocator_teleport",                        "Advanced Dislocator Teleport");
        add("key.draconicevolution.dislocator_blink",                           "Advanced Dislocator Blink");
        add("key.draconicevolution.dislocator_gui",                             "Advanced Dislocator GUI");
        add("key.draconicevolution.dislocator_up",                              "Advanced Dislocator Select Up");
        add("key.draconicevolution.dislocator_down",                            "Advanced Dislocator Select Down");
    }

    private void coinToken() {
        add(TTMContent.ITEM_COIN_BRONZE.get(),                          "Bronze Coin");
            addLore(TTMContent.ITEM_COIN_BRONZE.get(),                       "64 Can be traded for 1 Silver coin");
        add(TTMContent.ITEM_COIN_SILVER.get(),                          "Silver Coin");
            addLore(TTMContent.ITEM_COIN_SILVER.get(),                       "64 Can be traded for 1 Gold coin");
        add(TTMContent.ITEM_COIN_GOLD.get(),                            "Gold Coin");
            addLore(TTMContent.ITEM_COIN_GOLD.get(),                         "64 Can be traded for 1 Mithril coin");
        add(TTMContent.ITEM_COIN_MITHRIL.get(),                         "Mithril Coin");
            addLore(TTMContent.ITEM_COIN_MITHRIL.get(),                      "Very rare and valuable coin");
        add(TTMContent.ITEM_DARKSIGIL.get(),                            TextFormatting.DARK_PURPLE + "Dark Sigil");
            addLore(TTMContent.ITEM_DARKSIGIL.get(),                         "Dark symbol dropped by a minion");
        add(TTMContent.ITEM_FACTIONCOIN.get(),                          TextFormatting.BLUE + "Faction Token");
            addLore(TTMContent.ITEM_FACTIONCOIN.get(),                       "Use this to raise faction standing");
        add(TTMContent.ITEM_FACTIONTOKEN.get(),                         TextFormatting.DARK_AQUA + "Faction Coin");
            addLore(TTMContent.ITEM_FACTIONTOKEN.get(),                      "This can be traded for unique items");
        add(TTMContent.ITEM_CAVECOMPLETE.get(),                         TextFormatting.DARK_AQUA + "Cave completion Token");
            addLore(TTMContent.ITEM_CAVECOMPLETE.get(),                      "Proof of killing the troll");
        add(TTMContent.ITEM_WATCHERCOMPLETE.get(),                      TextFormatting.DARK_AQUA + "Watcher Token");
            addLore(TTMContent.ITEM_WATCHERCOMPLETE.get(),                   "Proof of defeating the Watcher");
    }

    private void creativeTabGroups() {
        add("itemGroup.tolkienmobs.tools",                              "TolkienTweaks Tools");
        add("itemGroup.tolkienmobs.mats",                               "TolkienTweaks Materials & World Gen");
        add("itemGroup.tolkienmobs.spawn",                              "TolkienTweaks Mobs");
        add("itemGroup.tolkienmobs.food",                               "TolkienTweaks Food");
        add("itemGroup.tolkienmobs.quest",                              "TolkienTweaks Quest Items");
        add("itemGroup.tolkienmobs.signs",                              "TolkienTweaks Signs");
    }

    private void potion() {
        add("effect.tolkienmobs.ent_draught",                           "Ent Draught");
        add("item.minecraft.potion.effect.ent_draught",                 "Potion of Ent Draught");
        add("item.minecraft.splash_potion.effect.ent_draught",          "Splash Potion of Ent Draught");
        add("item.minecraft.lingering_potion.effect.ent_draught",       "Lingering Potion of Ent Draught");
        add("item.minecraft.tipped_arrow.effect.ent_draught",           "Arrow of Ent Draught");
        add("effect.tolkienmobs.elf_vitality",                          "Elvish Life-span");
        add("item.minecraft.potion.effect.elf_vitality",                "Potion of Elvish Life-span");
        add("item.minecraft.splash_potion.effect.elf_vitality",         "Splash Potion of Elvish Life-span");
        add("item.minecraft.lingering_potion.effect.elf_vitality",      "Lingering Potion of Elvish Life-span");
        add("item.minecraft.tipped_arrow.effect.elf_vitality",          "Arrow of Elvish Life-span");
        add("effect.tolkienmobs.inventory_corrosion",                           "Corrosion");
        add("item.minecraft.potion.effect.inventory_corrosion",                 "Potion of Corrosion");
        add("item.minecraft.splash_potion.effect.inventory_corrosion",          "Splash Potion of Corrosion");
        add("item.minecraft.lingering_potion.effect.inventory_corrosion",       "Lingering Potion of Corrosion");
        add("item.minecraft.tipped_arrow.effect.inventory_corrosion",           "Arrow of Corrosion");
        add("effect.tolkienmobs.personal_blacksmith",                           "Portable Blacksmith");
        add("item.minecraft.potion.effect.personal_blacksmith",                 "Potion of Portable Blacksmith");
        add("item.minecraft.splash_potion.effect.personal_blacksmith",          "Splash Potion of Portable Blacksmith");
        add("item.minecraft.lingering_potion.effect.personal_blacksmith",       "Lingering Potion of Portable Blacksmith");
        add("item.minecraft.tipped_arrow.effect.personal_blacksmith",           "Arrow of Portable Blacksmith");
        add("effect.tolkienmobs.dread_aura",                            "Great Dread");
        add("item.minecraft.potion.effect.dread_aura",                  "Potion of Great Dread");
        add("item.minecraft.splash_potion.effect.dread_aura",           "Splash Potion of Great Dread");
        add("item.minecraft.lingering_potion.effect.dread_aura",        "Lingering Potion of Great Dread");
        add("item.minecraft.tipped_arrow.effect.dread_aura",            "Arrow of Great Dread");
        add("effect.tolkienmobs.elven_nimbleness",                      "Elvish Nimbleness");
        add("item.minecraft.potion.effect.elven_nimbleness",            "Potion of Elvish Nimbleness");
        add("item.minecraft.splash_potion.effect.elven_nimbleness",     "Splash Potion of Elvish Nimbleness");
        add("item.minecraft.lingering_potion.effect.elven_nimbleness",  "Lingering Potion of Elvish Nimbleness");
        add("item.minecraft.tipped_arrow.effect.elven_nimbleness",      "Arrow of Elvish Nimbleness");
//        add("effect.tolkienmobs.sleepnesia",                          "Sleepnesia");
//        add("item.minecraft.potion.effect.sleepnesia",                "Potion of Sleepnesia");
//        add("item.minecraft.splash_potion.effect.sleepnesia",         "Splash Potion of Sleepnesia");
//        add("item.minecraft.lingering_potion.effect.sleepnesia",      "Lingering Potion of Sleepnesia");
//        add("item.minecraft.tipped_arrow.effect.sleepnesia",          "Arrow of Sleepnesia");
        add("effect.tolkienmobs.crippling_terror",                      "Paralysing Fear");
        add("item.minecraft.potion.effect.crippling_terror",            "Potion of Paralysing Fear");
        add("item.minecraft.splash_potion.effect.crippling_terror",     "Splash Potion of Paralysing Fear");
        add("item.minecraft.lingering_potion.effect.crippling_terror",  "Lingering Potion of Paralysing Fear");
        add("item.minecraft.tipped_arrow.effect.crippling_terror",      "Arrow of Paralysing Fear");
        add("effect.tolkienmobs.blessing_of_eru",                       "Eru's Blessing");
        add("item.minecraft.potion.effect.blessing_of_eru",             "Potion of Eru's Blessing");
        add("item.minecraft.splash_potion.effect.blessing_of_eru",      "Splash Potion of Eru's Blessing");
        add("item.minecraft.lingering_potion.effect.blessing_of_eru",   "Lingering Potion of Eru's Blessing");
        add("item.minecraft.tipped_arrow.effect.blessing_of_eru",       "Arrow of Eru's Blessing");
//        add("effect.tolkienmobs.elemental_drowning",                          "Elemental Drowning");
//        add("item.minecraft.potion.effect.elemental_drowning",                "Potion of Elemental Drowning");
//        add("item.minecraft.splash_potion.effect.elemental_drowning",         "Splash Potion of Elemental Drowning");
//        add("item.minecraft.lingering_potion.effect.elemental_drowning",      "Lingering Potion of Elemental Drowning");
//        add("item.minecraft.tipped_arrow.effect.elemental_drowning",          "Arrow of Elemental Drowning");
        add("effect.tolkienmobs.elemental_lightning",                          "Elemental Lightning");
        add("item.minecraft.potion.effect.elemental_lightning",                "Potion of Elemental Lightning");
        add("item.minecraft.splash_potion.effect.elemental_lightning",         "Splash Potion of Elemental Lightning");
        add("item.minecraft.lingering_potion.effect.elemental_lightning",      "Lingering Potion of Elemental Lightning");
        add("item.minecraft.tipped_arrow.effect.elemental_lightning",          "Arrow of Elemental Lightning");
        add("effect.tolkienmobs.elemental_flight",                          "Elemental Flying");
        add("item.minecraft.potion.effect.elemental_flight",                "Potion of Elemental Flying");
        add("item.minecraft.splash_potion.effect.elemental_flight",         "Splash Potion of Elemental Flying");
        add("item.minecraft.lingering_potion.effect.elemental_flight",      "Lingering Potion of Elemental Flying");
        add("item.minecraft.tipped_arrow.effect.elemental_flight",          "Arrow of Elemental Flying");
        add("effect.tolkienmobs.elemental_burning",                     "Elemental Burning");
        add("item.minecraft.potion.effect.elemental_burning",           "Potion of Elemental Burning");
        add("item.minecraft.splash_potion.effect.elemental_burning",    "Splash Potion of Elemental Burning");
        add("item.minecraft.lingering_potion.effect.elemental_burning", "Lingering Potion of Elemental Burning");
        add("item.minecraft.tipped_arrow.effect.elemental_burning",     "Arrow of Elemental Burning");
//        add("effect.tolkienmobs.elemental_tornado",                          "Elemental Tornado");
//        add("item.minecraft.potion.effect.elemental_tornado",                "Potion of Elemental Tornado");
//        add("item.minecraft.splash_potion.effect.elemental_tornado",         "Splash Potion of Elemental Tornado");
//        add("item.minecraft.lingering_potion.effect.elemental_tornado",      "Lingering Potion of Elemental Tornado");
//        add("item.minecraft.tipped_arrow.effect.elemental_tornado",          "Arrow of Elemental Tornado");
    }

    private void enchants(){
        add("enchantment.tolkienmobs.balrogs_mark",                     "Balrog's Mark");
        add("enchantment.tolkienmobs.elven_longevity",                  "Elven Longevity");
        add("enchantment.tolkienmobs.gondor_resolve",                   "Gondorian Resolve");
    }

    private void food(){
        add(TTMContent.DRINK_ENT_DRAUGHT.get(),                         "Ent Draught");
        add(TTMContent.DRINK_PERSONAL_BLACKSMITH.get(),                 "Portable Blacksmith");
        add(TTMContent.DRINK_ELF_FLEETFOOT.get(),                       "Blessing of the Elves");
        add(TTMContent.DRINK_ELF_VITALITY.get(),                        "Vitality");
        add(TTMContent.DRINK_ERU_BLESSING.get(),                        "Blessing of Eru Iluvatar");
        add(TTMContent.MIRUVOR.get(),                                   "Miruvor");
        add(TTMContent.GROG.get(),                                      "Grog");
        add(TTMContent.LEMBAS.get(),                                    "Lembas");
        add(TTMContent.HONEY_CAKE.get(),                                "Honey Cake");
        add(TTMContent.CRAM.get(),                                      "Cram");
        add(TTMContent.MONSTER_FLESH.get(),                             "Monster Flesh");
        add(TTMContent.INSECT.get(),                                    "Frog Bait");
        add(TTMContent.GOLDEN_INSECT.get(),                             "Fancy Frog Bait");
        add(TTMContent.TREE_ACORN.get(),                                "Acorn");
        add(TTMContent.GOLDEN_TREE_ACORN.get(),                         "Golden Acorn");
        add(TTMContent.FOOD_HONEY.get(),                                "Honey");
    }

    private void records() {
        add(TTMContent.RECORD_RIVENDELL.get(),                          "Travelling Music");
            add("item.tolkienmobs." + TTMContent.RECORD_RIVENDELL.get() + ".desc",          "Riders of Rivendell");
        add(TTMContent.RECORD_LOTHLORIEN.get(),                         "Travelling Music");
            add("item.tolkienmobs." + TTMContent.RECORD_LOTHLORIEN.get() + ".desc",         "The Light of Lothlorien");
        add(TTMContent.RECORD_EREBOR.get(),                             "Travelling Music");
            add("item.tolkienmobs." + TTMContent.RECORD_EREBOR.get() + ".desc",             "All That Glitters in Erebor");
        add(TTMContent.RECORD_WILLOW.get(),                             "Travelling Music");
            add("item.tolkienmobs." + TTMContent.RECORD_WILLOW.get() + ".desc",             "Willow Song");
        add(TTMContent.RECORD_MINASTIRITH.get(),                        "Travelling Music");
            add("item.tolkienmobs." + TTMContent.RECORD_MINASTIRITH.get() + ".desc",        "White Tree of Gondor");
        add(TTMContent.RECORD_EDORAS.get(),                             "Travelling Music");
            add("item.tolkienmobs." + TTMContent.RECORD_EDORAS.get() + ".desc",             "Wake of Edoras");
        add(TTMContent.RECORD_WBATTLE.get(),                            "Travelling Music");
            add("item.tolkienmobs." + TTMContent.RECORD_WBATTLE.get() + ".desc",            "Fly My Pretties!");
        add(TTMContent.RECORD_MURDERFROG.get(),                         "Travelling Music");
            add("item.tolkienmobs." + TTMContent.RECORD_MURDERFROG.get() + ".desc",         "Ballad of Murder-Frog");
        add(TTMContent.RECORD_REDER.get(),                              "Travelling Music");
            add("item.tolkienmobs." + TTMContent.RECORD_REDER.get() + ".desc",              "Evil Incarnate");
        add(TTMContent.RECORD_FUMBLE.get(),                             "Travelling Music");
            add("item.tolkienmobs." + TTMContent.RECORD_FUMBLE.get() + ".desc",             "Bumbling Oaf");
        add(TTMContent.RECORD_BOMBADIL.get(),                           "Travelling Music");
            add("item.tolkienmobs." + TTMContent.RECORD_BOMBADIL.get() + ".desc",           "Mystery of Tom Bombadil");
        add(TTMContent.RECORD_HOBBITS.get(),                            "Travelling Music");
            add("item.tolkienmobs." + TTMContent.RECORD_HOBBITS.get() + ".desc",            "Concerning Hobbits - Remix");
    }

    private void tools(){
        add(TTMContent.AXE_MITHRIL.get(),                               "Mithril Axe");
        add(TTMContent.SWORD_MITHRIL.get(),                             "Mithril Sword");
        add(TTMContent.HOE_MITHRIL.get(),                               "Mithril Hoe");
        add(TTMContent.SHOVEL_MITHRIL.get(),                            "Mithril Shovel");
        add(TTMContent.PICKAXE_MITHRIL.get(),                           "Mithril Pickaxe");
        add(TTMContent.AXE_MORGULIRON.get(),                            "Morgul Iron Axe");
        add(TTMContent.SWORD_MORGULIRON.get(),                          "Morgul Iron Sword");
        add(TTMContent.HOE_MORGULIRON.get(),                            "Morgul Iron Hoe");
        add(TTMContent.SHOVEL_MORGULIRON.get(),                         "Morgul Iron Shovel");
        add(TTMContent.PICKAXE_MORGULIRON.get(),                        "Morgul Iron Pickaxe");
        add(TTMContent.SWORD_WITCHKING.get(),                           TextFormatting.DARK_RED + "Sword of the Witch-king");

        add(TTMContent.TRINKET_AMULET.get(),                            "Magic Amulet of %s");
    }

    private void entities(){
        add(EntityGenerator.ENTITY_TTM_RAT.get(),                       "Rat");
    }



    //region Helpers

    @Override
    public void add(Block key, String name) {
        if (key != null) super.add(key, name);
    }

    @Override
    public void add(Item key, String name) {
        if (key != null) super.add(key, name);
    }


    //endregion

    public String getName() {
        return "Tolkien Tweaks - Mobs Edition English Translation";
    }
}
